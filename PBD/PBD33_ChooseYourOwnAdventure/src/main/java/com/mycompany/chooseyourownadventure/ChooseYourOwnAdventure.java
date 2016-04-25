/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.chooseyourownadventure;

import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class ChooseYourOwnAdventure {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);

        String choice1, choice2, choice3, choice4, choice5, choice6, choice7;
        boolean valid = false;

        System.out.println("~~~Welcome to Archibald's Journey~~~");
        System.out.println("");
        while (!valid) {
            System.out.println("Good morning, Archibald. Thou hast arrived at ye eastern river beyond\n"
                    + "Ye Mountains of Cragg. The river is moving too swiftly to traverse.\n"
                    + "Ye see a keg on ye shore. Inspect ye keg? [yes/no]");
            choice1 = keyboard.nextLine();
            System.out.println("");

            if (choice1.equalsIgnoreCase("yes")) {
                while (!valid) {
                    System.out.println("Ye inspected ye keg. Ye keg doth empty. Ye keg is large enough to"
                            + "\n\"enter\". Doth ye \"enter\" ye keg or \"leave alone\" ye keg?");
                    choice2 = keyboard.nextLine();
                    System.out.println("");
                    if (choice2.equalsIgnoreCase("enter")) {
                        while (!valid) {
                            System.out.println("Ye entered ye keg. Thou feel cozy. Ye are sheltered from ye sun's rays.\n"
                                    + "Ye hear footsteps approaching. Will thou \"greet\" ye travelers or \"remain cozy\"?");
                            choice3 = keyboard.nextLine();
                            System.out.println("");
                            if (choice3.equalsIgnoreCase("greet")) {
                                System.out.println("Ye peek ye eyes above ye opening to ye keg. Ye see a maiden in "
                                        + "possession\nof beer. Ye maiden handed thou a beer and continues her"
                                        + "journey.\nYe are content with life.\nThus is a happy ending for ye Archibald.");

                            } else if (choice3.equalsIgnoreCase("remain cozy")) {
                                System.out.println("Ye remain cozy in ye keg. Ye spend the rest of ye days in ye keg.\n"
                                        + "Thus is a happy ending for ye Archibald.");
                            valid = true;
                            } else {
                                System.out.println("I do not know thy command");
                                System.out.println("");
                            }
                        }
                    } else if (choice2.equalsIgnoreCase("leave alone")) {
                        while (!valid) {
                            System.out.println("Ye chose to leave ye keg alone. As ye turned ye back to ye keg,\n"
                                    + "a man appears before ye, donning a purple robe. Ye man informs thou\nof a "
                                    + "town to ye south. Woulds't thou \"follow\" ye man or \"stand\" by\n"
                                    + "ye keg?");
                            choice6 = keyboard.nextLine();
                            System.out.println("");
                            if (choice6.equalsIgnoreCase("follow")) {
                                System.out.println("Ye followed ye man towards ye town. Bandits appear"
                                        + " before ye man and thee. Ye man disappears\nin a cloud of smoke."
                                        + " Ye are left abandoned. Ye Archibald is beaten to death by ye\n"
                                        + "bandits. Thus is a sad end for ye Archibald.");
                            valid = true;
                            } else if (choice6.equalsIgnoreCase("stand")) {
                                System.out.println("Ye stand by ye keg in silence. Ye man is enthralled by"
                                        + " the sight\nof ye keg. Ye man rushes towards the keg, knocking"
                                        + " ye into ye river.\nYe hast drowned. Thus is a sad end for ye "
                                        + "Archibald.");
                            valid = true;
                            } else {
                                System.out.println("I do not know thy command");
                                System.out.println("");
                            }//end of ye man
                        }//end leave ye keg alone
                    }
                }
            } else if (choice1.equalsIgnoreCase("no")) {//inspected keg
                while (!valid) {
                    System.out.println("Ye chose not to inspect ye keg. Ye see ye maiden walking towards you.\n"
                            + "She seems to be carrying something heavy. Will ye \"speak\" to \n"
                            + "ye maiden or stand in \"silence\"?");
                    choice4 = keyboard.nextLine();
                    valid = false;
                    System.out.println("");
                    if (choice4.equalsIgnoreCase("speak")) {
                        while (!valid) {
                            System.out.println("Ye spoke to ye maiden. Ye wench is carrying ye beer in a large satchel\n"
                                    + "on her back. Ye are thirsty for ye beer. Ye wench looks strong.\nWil l"
                                    + "ye \"ask\" for ye beer or \"steal\" ye satchel");
                            choice5 = keyboard.nextLine();
                            System.out.println("");
                            if (choice5.equalsIgnoreCase("ask")) {
                                System.out.println("Ye ask ye wench for a beer. She obliges ye Archibald. Ye feel\n"
                                        + "content with ye beer. Ye maiden climbs into ye keg and becomes cozy.\n"
                                        + "Ye feel sad that ye shall never feel cozy in ye keg. Thus is a bittersweet\n"
                                        + "end to ye Archibald.");
                            valid = true;

                            } else if (choice5.equalsIgnoreCase("steal")) {
                                System.out.println("Ye attempt to steal ye beer from don maiden. Ye art overpowered\n"
                                        + "by ye maiden's strength. Ye maiden shows no mercy and thou art pummeled\n"
                                        + "to death by ye maiden. Thus is a sad ending for ye Archibald");
                            valid = true;
                            } else {
                                System.out.println("I do not know thy command");
                                System.out.println("");
                            }
                        }
                    } else if (choice4.equalsIgnoreCase("silence")) {
                        while (!valid){
                        System.out.println("Ye stand in silence as ye maiden approaches. Ye maiden stops in\n"
                                + "front of ye keg. Ye maiden climbs inside and becomes cozy. Ye Archibald\n"
                                + "remains thirsty and becomes saddend that ye is not cozy in don keg. Willist\n"
                                + "thou \"push\" ye keg into don river or willist thouh \"be on ye way\"?");
                        choice7 = keyboard.nextLine();
                        System.out.println("");
                        if (choice7.equalsIgnoreCase("push")) {
                            System.out.println("Out of jealousy, ye hast pushed ye keg in don river. Ye feel guilty,\n"
                                    + "thisty, and saddend that ye doth not feelest cozy. Thus is a sad end\n"
                                    + "for ye Archibald.");
                            valid = true;
                        } else if (choice7.equalsIgnoreCase("be on ye way")) {
                            System.out.println("Thou art on ye way. Thou feeled saddend and not cozy. Thou\n"
                                    + "shalt not ever feel cozy in ye keg. Thus is a sad end for ye Archibald.");
                            valid = true;
                        } else {
                            System.out.println("I do not know thy command");
                            System.out.println("");
                        }
                    }
                    }
                }
            } else {//did not inspect keg
                System.out.println("I do not know thy command.");
                System.out.println("");

            }
        }

    }

}
