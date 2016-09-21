<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.angel.dao.{MODULE}.{POJONAME}Mapper">
	<!-- 加载myBatis中实体类的sql映射语句文件 -->
	<resultMap type="org.ibase4j.model.{MODULE}.{POJONAME}" id="BaseResultMap">
		<id column="id_" property="Id" jdbcType="VARCHAR" />
		{POJOKEYTOCOLUMN}
	</resultMap>
	
	<!-- 数据库表字段列  -->
	<sql id="{POJONAME}_Column_List">
		{POJOCOLUMN}
	</sql>
	
	<!-- 保存一条记录 -->
	<insert id="insert" parameterType="java.util.Map">
		INSERT INTO {POJOTABLE}
		({POJOCOLUMN})
		VALUES
		({POJOKEYS})
	</insert>
	
	<!-- 根据id更新某些字段 -->
	<update id="updateByPrimaryKey" parameterType="org.ibase4j.model.{MODULE}.{POJONAME}" >
		update {POJOTABLE} 
		<set>
			{POJOUPDATESETS}
		</set>
		where id_=#{id}
	</update>
	
	<!-- 删除一条记录 -->
	<delete id="deleteByPrimaryKey" parameterType="java.lang.String">
		DELETE FROM {POJOTABLE} WHERE id_=#{id}
	</delete>
	
	<!-- 查询列表-->
	<select id="query" parameterType="java.util.Map" resultType="java.lang.String">
		SELECT id_ FROM {POJOTABLE}
		<where>
			{POJOWHERE}
		</where>
		ORDER BY id_ DESC
	</select>
	
	<select id="selectByPrimaryKey" parameterType="java.lang.String" resultMap="BaseResultMap">
		SELECT
		<include refid="{POJONAME}_Column_List" />
		FROM {POJOTABLE} WHERE id_=#{id}
	</select>
	
	<select id="selectAll" resultMap="BaseResultMap">
		select
		<include refid="{POJONAME}_Column_List" />
		from {POJOTABLE}
	</select>
</mapper>