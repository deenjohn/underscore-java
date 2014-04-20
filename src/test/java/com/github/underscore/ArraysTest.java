/*
 * The MIT License (MIT)
 * 
 * Copyright (c) 2014 Valentyn Kolesnikov
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.github.underscore;

import java.util.*;
import org.junit.Test;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

/**
 * Underscore library unit test.
 *
 * @author Valentyn Kolesnikov
 */
public class ArraysTest {
  
  public <T> List<T>array(T ... items) {
    return asList(items);
  }

  public <T> List<T>array() {
    return Collections.<T>emptyList();
  }

  @Test
  public void testFirst() {
    // from js
    assertEquals("can pull out the first element of an array", 1, _.first(array(1,2,3)));
    assertEquals("can pass an index to first", array(), _.first(array(1,2,3), 0));
    assertEquals("can pass an index to first", array(1, 2), _.first(array(1,2,3), 2));
    assertEquals("can perform OO-style 'first()'", 1, new _(array(1,2,3)).first());
    List<?> result = _.map(array(array(1,2,3), array(1,2,3)), new Function1<List<Integer>, Integer>() {
        public Integer apply(List<Integer> item) {
            return _.first(item);
        }
    });
    assertEquals("works well with _.map", array(1,1), result);
    assertEquals("works on an arguments object", 4, _.first(new Integer[]{4, 3, 2, 1}));

    // extra
    assertEquals("can pass an index of 1 to first", array(1), _.first(array(1,2,3), 1));
    assertEquals("can perform OO-style 'first()' with index passed", array(4,5), new _(array(4,5,6,7)).first(2));  

    assertEquals("aliased as 'head'", 1, _.head(array(1,2,3)));
    assertEquals("aliased as 'head'", array(), _.head(array(1,2,3), 0));

    // docs
    assertEquals(5, _.first(array(5, 4, 3, 2, 1)));
    assertEquals(array(5, 4, 3), _.first(array(5, 4, 3, 2, 1), 3));
  }

  @Test
  public void testRest() {
    List<Integer> numbers = array(1,2,3,4);
    
    // from js
    assertEquals("working rest()", array(2,3,4), _.rest(numbers));
    assertEquals("working rest(0)", array(1,2,3,4), _.rest(numbers, 0));
    assertEquals("rest can take an index", array(3,4), _.rest(numbers, 2));
    
    assertEquals("aliased as tail and works on arguments", array(2,3,4), new _(array(1,2,3,4)).tail());
    List<List<Integer>> result = _.map(array(array(1,2,3), array(1,2,3)), new Function1<List<Integer>, List<Integer>>() {
        public List<Integer> apply(List<Integer> vals) {
            return _.rest(vals);
        }
    });
    assertEquals("works well with _.map", array(2, 3, 2, 3), _.flatten(result));
    
    // extra
    assertEquals(array('b','c'), _.tail(array('a','b','c')));
    
    // docs
    assertEquals(array(4, 3, 2, 1), _.rest(array(5, 4, 3, 2, 1)));
  }
  
}