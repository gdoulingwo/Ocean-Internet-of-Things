package org.linkworld.ocean.test;

/*
 *@Author  liu
 *@Since   2021/11/6  12:06
 */


import org.junit.Test;
import org.linkworld.ocean.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CrudTest {

   @Autowired
   private SysUserMapper sysUserMapper;

   @Test
   public void test() {
       System.out.println(sysUserMapper);
   }

}
