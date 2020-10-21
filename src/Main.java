import java.util.Random;

public class Main {

    public static int bossHealth = 700;
    public static int bossDamage = 20;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {270, 260, 250, 200, 500};
    public static int[] heroesDamages = {20, 25, 15, 0, 5};
    public static String[] heroesAttackType = {"Physical",
            "Magical", "Kinetic", "Medic", "Golem"};
    public static int roundCounter = 0;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }

    public static void bossAngryState() {
        if (bossHealth <= 200) {
            Random r = new Random();
            int healthRand = r.nextInt(31) + 20;
            int damageRand = r.nextInt(11) + 10;
            bossHealth = bossDamage + healthRand;
            bossDamage = bossDamage + damageRand;
            System.out.println("Boss became angry: " +
                    "increased health by " + healthRand
                    + " and damage " + damageRand);
        }
    }

    public static void changeBossDefence() {
        Random r = new Random();
        int randomIndex = r.nextInt(heroesAttackType.length); // 0, 1, 2
        bossDefenceType = heroesAttackType[randomIndex];
        System.out.println(bossDefenceType);
    }

    public static void RandomHero() {
        Random r = new Random();
        int HeroIndex = r.nextInt(heroesAttackType.length);
        System.out.println(HeroIndex);
    }

    public static void GolemSkill() {
        for (int i = 0; i < heroesHealth.length; i++) {

            if (heroesHealth[i] > 0 && heroesHealth[4] > 0 && bossHealth > 0) ;
            {


                int a;
                int b;
                a = bossDamage + bossDamage / 5;
                b = (bossDamage / 5) * 4;
                if (heroesHealth[i] == heroesHealth[4]) {
                    heroesHealth[4] = heroesHealth[4] - a;
                } else {
                    heroesHealth[i] = heroesHealth[i] - b;
                }

            }


        }
    }

    public static void round() {
        roundCounter++;
        changeBossDefence();
        bossAngryState();
        // GolemSkill();
        bossHits();
        heroesHit();
        printStatistics();



    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamages.length; i++) {
            // System.out.println(heroesHealth[i]);
            if (bossHealth > 0 && heroesHealth[i] > 0) {
                if (heroesAttackType[i] == bossDefenceType) {
                    Random r = new Random();
                    int coeff = r.nextInt(7) + 2; // 2,3,4,5,6,7,8
                    if (bossHealth - heroesDamages[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamages[i] * coeff;
                    }
                    System.out.println(heroesAttackType[i] +
                            " critical damage " + heroesDamages[i] * coeff);
                } else {
                    if (bossHealth - heroesDamages[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamages[i];
                    }
                }
            }
            Random r = new Random();
            int HeroIndex = r.nextInt(heroesAttackType.length - 1);
            //System.out.println("kakoi gerroi" + HeroIndex);
            if (heroesHealth[3] - bossDamage <= 0)
                heroesHealth[3] = 0;

            else if (heroesHealth[HeroIndex] < 100) {
                heroesHealth[HeroIndex] = heroesHealth[HeroIndex] + 100;
                // System.out.println( "sdgsg"+heroesHealth[HeroIndex]);
            } else if (heroesHealth[HeroIndex] <= 0) {
                break;
            }
        }
    }

    public static void bossHits() {

        for (int i = 0; i < heroesHealth.length; i++) {

            //System.out.println("Hero ---" + heroesHealth[4])
            Random r = new Random();
            int HeroIndex = r.nextInt(heroesAttackType.length);

            if (heroesHealth[i] > 0 && bossHealth > 0) {
                int a;
                int b;
                a = bossDamage + bossDamage / 5;
                b = (bossDamage / 5) * 4;
                if (heroesHealth[i] == heroesHealth[4]) {
                    heroesHealth[4] = heroesHealth[4] - a;
                } else {
                    heroesHealth[i] = heroesHealth[i] - b;
                }

                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {

                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }


            }
        }
    }

    public static void printStatistics() {
        System.out.println("ROUND ----- " + roundCounter);
        System.out.println("______________");
        System.out.println("Boss health: " + bossHealth);
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] +
                    " health: " + heroesHealth[i]);
        }
        System.out.println("______________");
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }

        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }

        return allHeroesDead;
    }
}
