/*
 *    Copyright 2009-2023 the original author or authors.
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
package org.apache.ibatis.type;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation that specify alias name.
 * <p>
 * <b>How to use:</b>
 *
 * <pre>
 * &#064;Alias("Email")
 * public class UserEmail {
 *   // ...
 * }
 * </pre>
 *
 * @author Clinton Begin
 *
 * 类型别名注解
 * 比如：UserEmail 实体类标识了注解 @Alias("ue") ,那么在XML中的 ResultHandle/ parameterType 就可以不写UserEmail的类路径而直接写别名 ue
 *
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Alias {
  /**
   * Return the alias name.
   *
   * @return the alias name
   */
  String value();
}
