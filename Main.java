import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
         /* 「SORU」
            10 Elemanlı bir şehir isimlerinden oluşan bir dizi tanımlayınız.
            Programın rastgele listeden bir şehir seçip karakter sayısı kadar _ _ _ _ _ _  oluşturan kullanıcı tarafından tahmin edilmeye çalışan
            Adam Asmaca programın Java programını yazınız. (Kullanıcı hataları dikkate  alın harf yerine sayı girmesi, büyük küçük harf girmesi,
            Süreye göre bir puanlama sistemi oluşturunuz. 0-20 sn  100 puan  20 - 30 90 puan gibi )
         */
         /* 「AÇIKLAMA」
             --
            |  |
            |  ó
            | /|\
            | /\
        */
        Scanner scanner = new Scanner(System.in);
        Boolean flag = false;
        int faultNum = 0;

        String[] citiesList = {"Antalya", "Bursa", "Çanakkale", "Eskişehir", "Hatay", "İstanbul", "İzmir", "Kütahya", "Niğde", "Trabzon"}, scrtCityLttrs;
        ArrayList<String> citiesArr = new ArrayList<>(), secretCityLetters = new ArrayList<>(), secretCity = new ArrayList<>();
        for (String i: citiesList) citiesArr.add(i);

        String randomCity = randomCitySelector(citiesArr);
        scrtCityLttrs = randomCity.split("");

        for (int i = 0; i < randomCity.length(); i++) {
            secretCity.add("_");
            secretCityLetters.add(scrtCityLttrs[i]);
        }

        // Display
        System.out.println(secretCity);
        System.out.println(secretCity.size() + " harfli");

        while (!flag) {
            String guess = scanner.nextLine().toLowerCase();

            for (int i = 0; i < randomCity.length(); i++) {
                if (secretCityLetters.get(i).contains(guess.toLowerCase()) || secretCityLetters.get(i).contains(guess.toUpperCase())) {
                    secretCity.set(i, secretCityLetters.get(i));
                    System.out.println(secretCity);
                }
            }
            if (!secretCityLetters.contains(guess.toLowerCase()) && !secretCityLetters.contains(guess.toUpperCase())) {
                System.out.println("Bu harf bu şehirde yok! Yeni bir tane deneyin...");
                faultNum++;
                hangman(faultNum);
            } else System.out.println("Doğru harf! Tekrar bir harf giriniz");

            if (secretCity.equals(secretCityLetters) || faultNum == 6) {
                System.out.println("Şehri buldunuz! Tekrar oynamaya devam etmek ister misiniz? (y/n)");
                String playAgain = scanner.nextLine().toLowerCase();
                if (playAgain == "y") {

                } else flag = true;
            }

        }

    }

    public static String randomCitySelector(ArrayList<String> citiesArr) {
        Random randomizer = new Random();

        return citiesArr.get(randomizer.nextInt(citiesArr.size()));
    }

    public static void hangman(int faultNum) {
        String ixi = "    \n", ixii = " -- \n", i;
        switch (faultNum) {
            case 1 -> System.out.println(ixi + "    \n" + "    \n" + "    \n" + "  /\\");
            case 2 -> System.out.println(ixi + "    \n" + "    \n" + "  /|\\\n" + "  /\\");
            case 3 -> System.out.println(ixi + "    \n" + "   ó\n" + "  /|\\\n" + "  /\\");
            case 4 -> System.out.println(ixii + "    \n" + "   ó\n" + "  /|\\\n" + "  /\\");
            case 5 -> System.out.println(ixii + "|   \n" + "|  ó\n" + "| /|\\\n" + "| /\\");
            case 6 -> System.out.println(ixii + "| | \n" + "|  ó\n" + "| /|\\\n" + "| /\\");
        }
    }

}
