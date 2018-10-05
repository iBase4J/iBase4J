(function() {
  var bind = function(fn, me){ return function(){ return fn.apply(me, arguments); }; },
    extend = function(child, parent) { for (var key in parent) { if (hasProp.call(parent, key)) child[key] = parent[key]; } function ctor() { this.constructor = child; } ctor.prototype = parent.prototype; child.prototype = new ctor(); child.__super__ = parent.prototype; return child; },
    hasProp = {}.hasOwnProperty;

  (function($) {
    this.AddressPickerResult = (function() {
      function AddressPickerResult(placeResult, fromReverseGeocoding) {
        this.placeResult = placeResult;
        this.fromReverseGeocoding = fromReverseGeocoding != null ? fromReverseGeocoding : false;
        this.latitude = this.placeResult.geometry.location.lat();
        this.longitude = this.placeResult.geometry.location.lng();
      }

      AddressPickerResult.prototype.addressTypes = function() {
        var component, i, j, len, len1, ref, ref1, type, types;
        types = [];
        ref = this.addressComponents();
        for (i = 0, len = ref.length; i < len; i++) {
          component = ref[i];
          ref1 = component.types;
          for (j = 0, len1 = ref1.length; j < len1; j++) {
            type = ref1[j];
            if (types.indexOf(type) === -1) {
              types.push(type);
            }
          }
        }
        return types;
      };

      AddressPickerResult.prototype.addressComponents = function() {
        return this.placeResult.address_components || [];
      };

      AddressPickerResult.prototype.address = function() {
        return this.placeResult.formatted_address;
      };

      AddressPickerResult.prototype.nameForType = function(type, shortName) {
        var component, i, len, ref;
        if (shortName == null) {
          shortName = false;
        }
        ref = this.addressComponents();
        for (i = 0, len = ref.length; i < len; i++) {
          component = ref[i];
          if (component.types.indexOf(type) !== -1) {
            return (shortName ? component.short_name : component.long_name);
          }
        }
        return null;
      };

      AddressPickerResult.prototype.lat = function() {
        return this.latitude;
      };

      AddressPickerResult.prototype.lng = function() {
        return this.longitude;
      };

      AddressPickerResult.prototype.setLatLng = function(latitude, longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
      };

      AddressPickerResult.prototype.isAccurate = function() {
        return !this.placeResult.geometry.viewport;
      };

      AddressPickerResult.prototype.isReverseGeocoding = function() {
        return this.fromReverseGeocoding;
      };

      return AddressPickerResult;

    })();
    return this.AddressPicker = (function(superClass) {
      extend(AddressPicker, superClass);

      function AddressPicker(options) {
        if (options == null) {
          options = {};
        }
        this.markerDragged = bind(this.markerDragged, this);
        this.updateBoundsForPlace = bind(this.updateBoundsForPlace, this);
        this.updateMap = bind(this.updateMap, this);
        this.options = $.extend({
          local: [],
          datumTokenizer: function(d) {
            return Bloodhound.tokenizers.whitespace(d.num);
          },
          queryTokenizer: Bloodhound.tokenizers.whitespace,
          autocompleteService: {
            types: ["geocode"]
          },
          zoomForLocation: 16,
          reverseGeocoding: false,
          placeDetails: true,
          remote: 'fakeRemote'
        }, options);
        AddressPicker.__super__.constructor.call(this, this.options);
        if (this.options.map) {
          this.initMap();
        }
        this.placeService = new google.maps.places.PlacesService(document.createElement('div'));
      }

      AddressPicker.prototype.bindDefaultTypeaheadEvent = function(typeahead) {
        typeahead.bind("typeahead:selected", this.updateMap);
        typeahead.bind("typeahead:autocompleted", this.updateMap);
        return typeahead.bind("typeahead:cursorchanged", this.updateMap);
      };

      AddressPicker.prototype.initMap = function() {
        var markerOptions, ref, ref1;
        if ((ref = this.options) != null ? (ref1 = ref.map) != null ? ref1.gmap : void 0 : void 0) {
          this.map = this.options.map.gmap;
        } else {
          this.mapOptions = $.extend({
            zoom: 3,
            center: new google.maps.LatLng(0, 0),
            mapTypeId: google.maps.MapTypeId.ROADMAP,
            boundsForLocation: this.updateBoundsForPlace
          }, this.options.map);
          this.map = new google.maps.Map($(this.mapOptions.id)[0], this.mapOptions);
        }
        this.lastResult = null;
        markerOptions = $.extend({
          draggable: true,
          visible: false,
          position: this.map.getCenter(),
          map: this.map
        }, this.options.marker || {});
        this.marker = new google.maps.Marker(markerOptions);
        if (markerOptions.draggable) {
          return google.maps.event.addListener(this.marker, 'dragend', this.markerDragged);
        }
      };

      AddressPicker.prototype.search = function(query, sync, async) {
        var service;
        service = new google.maps.places.AutocompleteService();
        this.options.autocompleteService.input = query;
        return service.getPlacePredictions(this.options.autocompleteService, (function(_this) {
          return function(predictions) {
            $(_this).trigger('addresspicker:predictions', [predictions]);
            return async(predictions);
          };
        })(this));
      };

      AddressPicker.prototype.updateMap = function(event, place) {
        if (this.options.placeDetails) {
          return this.placeService.getDetails(place, (function(_this) {
            return function(response) {
              var ref;
              _this.lastResult = new AddressPickerResult(response);
              if (_this.marker) {
                _this.marker.setPosition(response.geometry.location);
                _this.marker.setVisible(true);
              }
              if (_this.map) {
                if ((ref = _this.mapOptions) != null) {
                  ref.boundsForLocation(response);
                }
              }
              return $(_this).trigger('addresspicker:selected', _this.lastResult);
            };
          })(this));
        } else {
          return $(this).trigger('addresspicker:selected', place);
        }
      };

      AddressPicker.prototype.updateBoundsForPlace = function(response) {
        if (response.geometry.viewport) {
          return this.map.fitBounds(response.geometry.viewport);
        } else {
          this.map.setCenter(response.geometry.location);
          return this.map.setZoom(this.options.zoomForLocation);
        }
      };

      AddressPicker.prototype.markerDragged = function() {
        if (this.options.reverseGeocoding) {
          return this.reverseGeocode(this.marker.getPosition());
        } else {
          if (this.lastResult) {
            this.lastResult.setLatLng(this.marker.getPosition().lat(), this.marker.getPosition().lng());
          } else {
            this.lastResult = new AddressPickerResult({
              geometry: {
                location: this.marker.getPosition()
              }
            });
          }
          return $(this).trigger('addresspicker:selected', this.lastResult);
        }
      };

      AddressPicker.prototype.reverseGeocode = function(position) {
        if (this.geocoder == null) {
          this.geocoder = new google.maps.Geocoder();
        }
        return this.geocoder.geocode({
          location: position
        }, (function(_this) {
          return function(results) {
            if (results && results.length > 0) {
              _this.lastResult = new AddressPickerResult(results[0], true);
              return $(_this).trigger('addresspicker:selected', _this.lastResult);
            }
          };
        })(this));
      };

      AddressPicker.prototype.getGMap = function() {
        return this.map;
      };

      AddressPicker.prototype.getGMarker = function() {
        return this.marker;
      };

      return AddressPicker;

    })(Bloodhound);
  })(jQuery);

}).call(this);
