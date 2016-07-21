/*
* Copyright (C) 2008 Happy Fish / YuQing
*
* FastDFS Java Client may be copied only under the terms of the GNU Lesser
* General Public License (LGPL).
* Please visit the FastDFS Home Page http://www.csource.org/ for more detail.
*/

package org.csource.common;

/**
* name(key) and value pair model
* @author Happy Fish / YuQing
* @version Version 1.04
*/
public class Hash
{
    protected Hash()
    {
    }
	
		public static int PJWHash(byte[] key)
		{
			return PJWHash(key, 0, key.length);
		}
		
		public static int PJWHash(byte[] key, int offset, int length)
		{
	    final int BitsInUnignedInt = (int)(4 * 8);
	    final int ThreeQuarters    = (int)((BitsInUnignedInt * 3) / 4);
	    final int OneEighth        = (int)(BitsInUnignedInt / 8);
	    final int HighBits         = (int)(0xFFFFFFFF) << 
	                                (BitsInUnignedInt - OneEighth);
	    int hash;
	    int test;
	 
	    hash = 0;
	    for (int i=offset; i<length; i++)
	    {
	        hash = (hash << OneEighth) + (key[i] > 0 ? key[i] : 256 + key[i]);
	        if ((test = hash & HighBits) != 0)
	        {
	            hash = ((hash ^ (test >> ThreeQuarters)) & (~HighBits));
	        }
	    }
	 
	    return hash;
  	}

		public static int Time33Hash(byte[] key)
		{
			return Time33Hash(key, 0, key.length);
		}
		
		public static int Time33Hash(byte[] key, int offset, int length)
		{
	    int hash;
	 
	    hash = 0;
	    for (int i=offset; i<length; i++)
	    {
	    	hash += (hash << 5) + (key[i] >= 0 ? key[i] : 256 + key[i]);
	    }

	    return hash;
		}
}
