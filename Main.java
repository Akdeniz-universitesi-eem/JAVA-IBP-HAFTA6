import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
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
            main fonksiyonu gereken değişkenlerin belirtilmesi ile başlar.
            * citiesList rastgele seçilen 10 şehrin dizisidir, bu dizi for enhanced döngüsü ile citiesArr ArrayListine aktarılıt
            * scrtCityLttrs, rastgele seçilen şehrin harflerinin bölünmesiyle oluşur, dizi for döngüsü ile secretCityLetters ArrayListine aktarılıt, secretCity ArrayListine
            harf kadar "_" eklenir. Bunlar sonradan terminalde değişecek elemanlar olacaktır.

            Sonrasında "randomCitySelector" fonk ile şehir seçilir ve randomCity değişkenine atanır.

            Şehirin kaç harfli olduğu oyuncuya bildirilir ve oyun başlar, bununla beraber sayaç da başlar.
            Kullanıcıdan tahmini alınır, eğer bir harf ise aşağıdaki işlemler yapılır, değil ise kullanıcıya uyarı verilir ve tekrar girdi istenir.
            Eğer harf ise ve şehrin isminde olan bir harf ise secretCity'nin o indeksteki değeri açılır, secretCityLetters'deki değer ile değişir.
            Eğer şehrin isminde olmayan bir harf ise kullanıcıya uyarı verilir, uyarı sayısı arttırlır ve ona göre "hangman" fonksiyonundaki son
            durum terminale yazılır.

            Şehrin doğru bilinmesi ya da adamın asılması sonucu oyun biter. Süre, kelime uzunluğu ve hata sayısına göre bir skor hesaplanır.
            Kullanıcıya oyunu tekrar oynamak isteyip istemediği sorulur, istemezse oyun biter.
            İsterse gerekli değişkenler sıfırlanır, yeni bir rastgele şehir seçilir ve devam edilir.
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
        System.out.println(secretCity.size() + " harfli\n");

        while (!flag) {
            Instant start = Instant.now();
            String guess = scanner.nextLine().toLowerCase();
            if ("abcçdefgğhıijklmnoöprsştuüvyzABCÇDEFGĞHIİKJLMNOÖPRSŞTUÜVYZ".contains(guess)) {
                for (int i = 0; i < randomCity.length(); i++) {
                    if (secretCityLetters.get(i).contains(guess.toLowerCase()) || secretCityLetters.get(i).contains(guess.toUpperCase())) {
                        secretCity.set(i, secretCityLetters.get(i));
                        System.out.println("\n" + String.join("", secretCity));
                    }
                }
                if (!secretCityLetters.contains(guess.toLowerCase()) && !secretCityLetters.contains(guess.toUpperCase())) {
                    System.out.println("Bu harf bu şehirde yok! Yeni bir tane deneyin...\n");
                    faultNum++;
                    hangman(faultNum);
                } else System.out.println("Doğru harf! Tekrar bir harf giriniz\n");
            } else System.out.println("Geçersiz bir karakter girdiniz! Tekrar deneyin.\n");

            if (secretCity.equals(secretCityLetters) || faultNum == 6) {
                Instant end = Instant.now();
                Duration timeElapsed = Duration.between(start, end);
                long timeSec = timeElapsed.toSeconds();
                scoreCalc(faultNum, timeSec, secretCityLetters);
                System.out.println((faultNum == 6 ? "Şehri bulamadınız!" : "Şehri buldunuz!") + " Gizli şehir " + (faultNum == 6 ? "" : "tahmin ettiğiniz gibi ") + String.join("", secretCityLetters) + " idi. Oynamaya devam etmek ister misiniz? (y/n)");

                String playAgain = scanner.nextLine();
                if (playAgain == "y") {
                    randomCity = randomCitySelector(citiesArr);
                    Arrays.fill(scrtCityLttrs, null);
                    secretCity.clear(); secretCityLetters.clear();
                    faultNum = 0;

                    scrtCityLttrs = randomCity.split("");
                    for (int i = 0; i < randomCity.length(); i++) {
                        secretCity.add("_");
                        secretCityLetters.add(scrtCityLttrs[i]);
                    } flag = false;
                    System.out.println(secretCity);
                    System.out.println(secretCity.size() + " harfli bir şehir...\n");
                } else flag = true;
            }
        }
    }

    public static String randomCitySelector(ArrayList<String> citiesArr) {
        Random randomizer = new Random();
        return citiesArr.get(randomizer.nextInt(citiesArr.size()));
    }

    public static void hangman(int faultNum) {
        String ixi = "    \n", ixii = " -- \n", iixi = "    \n", iiixi = "    \n", iiixii = "   ó\n", iiixiii = "|  ó\n", ivxii = "  /|\\\n", ivxiii = "| /|\\\n", vxi = "  /\\", vxii = "| /\\";
        switch (faultNum) {
            case 1 -> System.out.println(ixi + iixi + iiixi + "    \n" + vxi);
            case 2 -> System.out.println(ixi + iixi + iiixi + ivxii + vxi);
            case 3 -> System.out.println(ixi + iixi + iiixii + ivxii + vxi);
            case 4 -> System.out.println(ixii + iixi + iiixii + ivxii + vxi);
            case 5 -> System.out.println(ixii + "|   \n" + iiixiii + ivxiii + vxii);
            case 6 -> System.out.println(ixii + "| | \n" + iiixiii + ivxiii + vxii);
        }
    }

    public static void scoreCalc(int faultNum, long duration, ArrayList word) {
        int scoreTime = 0, scoreFault = 100 - faultNum * 20, scoreWord = word.size() * 7;
        if (duration >= 0 && duration <= 3) scoreTime+= 100;
        else if (duration > 3 && duration <= 7) scoreTime+= 85;
        else if (duration > 7 && duration <= 11) scoreTime+= 70;
        else if (duration > 11 && duration <= 15) scoreTime+= 50;
        else if (duration > 15 && duration <= 20) scoreTime+= 30;
        else if (duration > 20) scoreTime+= 0;

        System.out.println("Zaman Skoru: " + scoreTime +  " (" + duration + " saniye)" + "\nHata Skoru: " + scoreFault +  " (" + faultNum + " hata)" + "\nKelime Uzunluğu Skoru: " + scoreWord +  " (" + word.size() + " harf)" + "\nToplam Skoru: " + (scoreTime + scoreFault +  scoreWord));
    }

}
