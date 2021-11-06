package org.linkworld.ocean.test;

/*
 *@Author  liu
 *@Since   2021/11/6  12:06
 */

import org.junit.Test;
import org.linkworld.ocean.mapper.OceanDynamicSensorMapper;
import org.linkworld.ocean.service.impl.SysRoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

@SpringBootTest
public class CrudTest {

   @Autowired
   private SysRoleServiceImpl sysRoleService;

   @Test
   public void test() {
         try{
            sysRoleService.hello();
         }catch (Exception e) {
            System.out.println("error" + e);
         }
   }

}
