/*
 *    Copyright 2009-2022 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.reflection.wrapper;

import java.util.List;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.property.PropertyTokenizer;

/**
 * @author Clinton Begin
 * 对象的包装类 实现： 获取属性的值、设置属性的值、查找某个属性 等等
 * 实现类：
 * 1. BeanWrapper
 *        Java Bean对象的包装，主要通过反射实现上述
 * 2. MapWrapper
 *        Map对象的包装，主要通过针对Map的操作实现
 * 3. CollectionWrapper
 *        集合对象的包装，仅支持添加元素等集合操作
 */
public interface ObjectWrapper {

  //获取属性的值
  Object get(PropertyTokenizer prop);

  //设置属性的值
  void set(PropertyTokenizer prop, Object value);

  //查找某个属性
  String findProperty(String name, boolean useCamelCaseMapping);

  //获取Getter名
  String[] getGetterNames();

  //获取Setter名
  String[] getSetterNames();

  //获取Setter类型
  Class<?> getSetterType(String name);

  //获取Getter类型
  Class<?> getGetterType(String name);

  //是否存在Setter
  boolean hasSetter(String name);

  //是否存在Getter
  boolean hasGetter(String name);

  MetaObject instantiatePropertyValue(String name, PropertyTokenizer prop, ObjectFactory objectFactory);

  //是否集合
  boolean isCollection();

  //集合添加元素
  void add(Object element);

  //集合追加集合
  <E> void addAll(List<E> element);

}
