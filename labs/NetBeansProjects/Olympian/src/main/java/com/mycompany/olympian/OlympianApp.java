package com.mycompany.olympian;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class OlympianApp {

    public static void main(String[] args) {

   ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

//        SkiJumper jumper = (SkiJumper) ctx.getBean("skiJumper");
//        System.out.println(jumper.competeInEvent());
//        Event skiJumpEvent = new CurlingEvent();
//
//        Olympian olympian = new Olympian(skiJumpEvent);
//        olympian.competeInEvent();

          Olympian usaSkiJumper = ctx.getBean("usaSkiJumper", Olympian.class);
          usaSkiJumper.competeInEvent();
          
          
          Olympian curlingDude = ctx.getBean("curlingDude", Olympian.class);
          curlingDude.competeInEvent();
    }

}
