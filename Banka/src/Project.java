import java.io.IOException;
import java.util.Scanner;

public class Project {
    public static void girisMenu() {
        System.out.println("Hesap islemleri icin 1'e basiniz.");
        System.out.println("Kart islemleri icin 2'ye basiniz.");
        System.out.println("Musteri olmak icin 3'e basiniz.");
        System.out.print("Sistemi kapatmak icin 0'a basiniz:");
    }

    private static boolean hesapBilgileri(Hesap customer) {
        boolean sonuc = false;
        try {
            Scanner scan = new Scanner(System.in);
            String hesapNo, TC;
            System.out.print("Hesap numaraniz:");
            hesapNo = scan.nextLine();
            System.out.print("TC kimlik numaraniz:");
            TC = scan.nextLine();
            if (customer.sorgula(hesapNo, TC)) {
                System.out.println("Hesapta bulunan bilgileriniz:");
                customer.hesapBilgileriAlma(hesapNo, TC);
                sonuc = true;
            } else {
                sonuc = false;
                hesapBilgileriYanlis(customer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sonuc;
    }

    private static void hesapBilgileriYanlis(Hesap customer) throws IOException {
        System.out.println("Girilen hesap bilgileriniz hatalidir!!!");
        System.out.println("Hesap bilgilerinizi tekrar denemek icin 1'e basiniz.");
        System.out.println("Ana menuye donmek icin 0'a basiniz.");
        Scanner scan = new Scanner(System.in);
        int sec;
        sec = scan.nextInt();
        switch (sec) {
            case 1:
                hesapBilgileri(customer);
                break;
            case 0:
                girisMenu();
                break;
        }
    }

    private static boolean kartBilgileri(Kart customer) {
        boolean sonuc = false;
        try {
            Scanner scan = new Scanner(System.in);
            String kartNo, TC;
            System.out.print("Kart numaraniz:");
            kartNo = scan.nextLine();
            System.out.print("TC kimlik numaraniz:");
            TC = scan.nextLine();
            if (customer.sorgula(kartNo, TC)) {
                System.out.println("Hesapta bulunan bilgileriniz:");
                customer.kartBilgileriAlma(kartNo, TC);
                sonuc = true;
            } else {
                kartBilgileriYanlis(customer);
                sonuc = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sonuc;
    }

    private static void kartBilgileriYanlis(Kart customer) throws IOException {
        System.out.println("Girilen kart bilgileriniz hatalidir!!!");
        System.out.println("Kart bilgilerinizi tekrar denemek icin 1'e basiniz.");
        System.out.println("Ana menuye donmek icin 0'a basiniz.");
        Scanner scan = new Scanner(System.in);
        int sec;
        sec = scan.nextInt();
        switch (sec) {
            case 1:
                kartBilgileri(customer);
                break;
            case 0:
                girisMenu();
                break;
        }
    }

    private static void eklemeMenu() {
        System.out.println("Hesap musterisi olmak icin 1'e basiniz");
        System.out.println("Kart musterisi olmak icin 2'ye basiniz");
        System.out.print("Ana menuye donmek icin 0'basÄ±nÄ±z:");
    }

    private static void hesapAnaMenu() {
        System.out.println("Musterinin para islemleri icin 1'e basiniz.");
        System.out.println("Musterinin hesabina ait bilgileri guncellemek icin 2'ye basiniz.");
        System.out.println("Musterinin hesabina ait bilgileri silmek icin 3'e basiniz.");
        System.out.println("Bankanin tum musterilerinin hesap bilgilerini gormek icin 4'e basiniz.");
        System.out.print("Basa donmek icin 0'a basiniz:");
    }

    private static void kartAnaMenu() {
        System.out.println("Musterinin para islemleri icin 1'e basiniz.");
        System.out.println("Musterinin kartina ait bilgileri guncellemek icin 2'ye basiniz.");
        System.out.println("Musterinin kartina ait bilgileri silmek icin 3'e basiniz.");
        System.out.println("Bankanin tum musterilerinin kart bilgilerini gormek icin 4'e basiniz.");
        System.out.print("Basa donmek icin 5'e basiniz:");
    }

    private static Hesap musteriHesapEkle(Hesap cust) throws IOException {
        Scanner scan = new Scanner(System.in);
        String a;
        System.out.print("Musterinin adi:");
        a = scan.nextLine();
        cust.setName(a);
        System.out.print("Musterinin Soyadi:");
        a = scan.nextLine();
        cust.setSurname(a);
        System.out.print("Musterinin TC:");
        a = scan.nextLine();
        cust.setTC(a);
        System.out.print("Musterinin Telefon Numarasi:");
        a = scan.nextLine();
        cust.setTelefonNo(a);
        System.out.print("Musterinin Sube Numarasi:");
        a = scan.nextLine();
        cust.setSubeNo(a);
        System.out.print("Musterinin Hesap Numarasi:");
        a = scan.nextLine();
        cust.setHesapNo(a);
        System.out.print("Musterinin Bakiyesi:");
        a = scan.nextLine();
        cust.setBakiye(a);

        return cust;
    }

    private static Kart musteriKartEkle(Kart cust) throws IOException {
        Scanner scan = new Scanner(System.in);
        String a;
        System.out.print("Musterinin adi:");
        a = scan.nextLine();
        cust.setName(a);
        System.out.print("Musterinin Soyadi:");
        a = scan.nextLine();
        cust.setSurname(a);
        System.out.print("Musterinin TC:");
        a = scan.nextLine();
        cust.setTC(a);
        System.out.print("Musterinin Telefon Numarasi:");
        a = scan.nextLine();
        cust.setTelefonNo(a);
        System.out.print("Musterinin Sube Numarasi:");
        a = scan.nextLine();
        cust.setSubeNo(a);
        System.out.print("Musterinin Hesap Numarasi:");
        a = scan.nextLine();
        cust.setHesapNo(a);
        System.out.print("Musteri Kart Numarasi:");
        a = scan.nextLine();
        cust.setKartNo(a);
        System.out.print("Musteri CVC:");
        a = scan.nextLine();
        cust.setCVC(a);
        System.out.print("Musterinin Bakiyesi:");
        a = scan.nextLine();
        cust.setBakiye(a);
        return cust;
    }

    private static boolean adminGiris() throws IOException {
        boolean sonuc = true;
        Admin bankaci = new Admin();
        Scanner scan = new Scanner(System.in);
        System.out.print("Kullanici Id:");
        String temp;
        temp = scan.nextLine();
        bankaci.setAdminId(temp);
        System.out.print("Şifre:");
        temp = scan.nextLine();
        bankaci.setPassword(temp);
        sonuc = bankaci.giris(bankaci);
        return sonuc;
    }

    private static void paraIslemleri() {
        System.out.println("Para çekme islemleri icin 1'e");
        System.out.println("Para yatirma islemleri icin 2'ye");
        System.out.println("Para transfer islemleri icin 3'e");
        System.out.print("Cikmak icin 0'a basiniz:");
    }

    private static void hesaplariGoster() throws IOException {
        Hesap cst = new Hesap();
        cst.hesaplariListele();
    }

    private static void kartlariGoster() throws IOException {
        Kart cst = new Kart();
        cst.kartlariListele();
    }

public static void main(String args[]) throws IOException {   
    int sec;
    Scanner scan = new Scanner(System.in);
    do {
        System.out.println("Sisteme girmek için 1'e");
        System.out.print("Kapatmak için 0'a basınız:");
        sec = scan.nextInt();
        switch (sec) {
            case 1: {
                if (adminGiris()) {
                    girisMenu();
                    sec = scan.nextInt();
                    switch (sec) {
                        case 1:
                            Hesap cust = new Hesap();
                            if (hesapBilgileri(cust)) {
                                cust.print();
                                hesapAnaMenu();
                            }
                            sec = scan.nextInt();
                            switch (sec) {
                                case 1:
                                    paraIslemleri();
                                    sec = scan.nextInt();
                                    switch (sec) {
                                        case 1:
                                            System.out.print("Hesabınızdan çekmek istediğiniz miktar:");
                                            String cekilecekMiktar;
                                            cekilecekMiktar = scan.nextLine();
                                            cust.paraCekme(cust, cekilecekMiktar);
                                            System.out.println("Yeni bakiyeniz: " + cust.getBakiye());
                                            System.out.println("Hesap giriş menüsüne yönlendiriliyorsunuz");
                                            hesapAnaMenu();
                                            sec = scan.nextInt();
                                            break;
                                        case 2:
                                            System.out.print("Hesabınıza yatırmak istediğiniz miktar:");
                                            String yatirilacakMiktar;
                                            yatirilacakMiktar = scan.nextLine();
                                            cust.paraYatirma(cust, yatirilacakMiktar);
                                            System.out.println("Yeni bakiyeniz: " + cust.getBakiye());
                                            System.out.println("Hesap giriş menüsüne yönlendiriliyorsunuz");
                                            hesapAnaMenu();
                                            sec = scan.nextInt();
                                            break;
                                        case 3:
                                            System.out.print("Para yatırmak istediğiniz kişinin hesap numarası:");
                                            Hesap tempCust = new Hesap();
                                            String hesapNo, TC;
                                            hesapNo = scan.nextLine();
                                            System.out.print("Para yatırmak istediğiniz kişinin TC kimlik numarası:");
                                            TC = scan.nextLine();
                                            if ((tempCust.sorgula(hesapNo, TC))) {
                                                tempCust.hesapBilgileriAlma(hesapNo, TC);
                                                String gonderilecekMiktar;
                                                System.out.print("Hesabınızdan transfer yapmak istediğiniz miktar:");
                                                gonderilecekMiktar = scan.nextLine();
                                                cust.paraTransferi(cust, tempCust, gonderilecekMiktar);
                                            }
                                            System.out.println("Hesap giriş menüsüne yönlendiriliyorsunuz");
                                            hesapAnaMenu();
                                            sec = scan.nextInt();
                                            break;
                                        case 0:
                                            System.out.println("Hesap giriş menüsüne yönlendiriliyorsunuz");
                                            hesapAnaMenu();
                                            sec = scan.nextInt();
                                            break;
                                    }
                                    break;

                                case 2:
                                    String newHesapNo;
                                    System.out.print("Yeni hesap numarası:");
                                    newHesapNo = scan.nextLine();
                                    cust.hesapGuncelle(cust, newHesapNo);
                                    System.out.println("Hesap giriş menüsüne yönlendiriliyorsunuz");
                                    hesapAnaMenu();
                                    sec = scan.nextInt();
                                    break;
                                case 3:
                                    cust.sil(cust.getHesapNo());
                                    System.out.println("Hesap giriş menüsüne yönlendiriliyorsunuz");
                                    hesapAnaMenu();
                                    sec = scan.nextInt();
                                    break;
                                case 4:
                                    hesaplariGoster();
                                    System.out.println("Hesap giriş menüsüne yönlendiriliyorsunuz");
                                    hesapAnaMenu();
                                    sec = scan.nextInt();
                                    break;
                                case 0:
                                    girisMenu();
                                    sec = scan.nextInt();
                                    break;
                            }
                            break;
                        case 2:
                            Kart customer = new Kart();
                            if (kartBilgileri(customer)) {
                                customer.print();
                                kartAnaMenu();
                            }
                            sec = scan.nextInt();
                            switch (sec) {
                                case 1:
                                    paraIslemleri();
                                    sec = scan.nextInt();
                                    switch (sec) {
                                        case 1:
                                            System.out.print("Kartınızdan çekmek istediğiniz miktar:");
                                            String cekilecekMiktar;
                                            cekilecekMiktar = scan.nextLine();
                                            customer.paraCekme(customer, cekilecekMiktar);
                                            System.out.println("Yeni bakiyeniz: " + customer.getBakiye());
                                            System.out.println("Kart giriş menüsüne yönlendiriliyorsunuz");
                                            kartAnaMenu();
                                            sec = scan.nextInt();
                                            break;
                                        case 2:
                                            System.out.print("Kartınıza yatırmak istediğiniz miktar:");
                                            String yatirilacakMiktar;
                                            yatirilacakMiktar = scan.nextLine();
                                            customer.paraYatirma(customer, yatirilacakMiktar);
                                            System.out.println("Yeni bakiyeniz: " + customer.getBakiye());
                                            System.out.println("Kart giriş menüsüne yönlendiriliyorsunuz");
                                            kartAnaMenu();
                                            sec = scan.nextInt();
                                            break;
                                        case 3:
                                            System.out.print("Para yatırmak istediğiniz kişinin hesap numarası:");
                                            Kart tempCust = new Kart();
                                            String hesapNo, TC;
                                            hesapNo = scan.nextLine();
                                            System.out.print("Para yatırmak istediğiniz kişinin TC kimlik numarası:");
                                            TC = scan.nextLine();
                                            if ((tempCust.sorgula(hesapNo, TC))) {
                                                tempCust.kartBilgileriAlma(hesapNo, TC);
                                                String gonderilecekMiktar;
                                                System.out.print("Kartınızdan transfer yapmak istediğiniz miktar:");
                                                gonderilecekMiktar = scan.nextLine();
                                                customer.paraTransferi(customer, tempCust, gonderilecekMiktar);
                                            }
                                            System.out.println("Hesap giriş menüsüne yönlendiriliyorsunuz");
                                            hesapAnaMenu();
                                            sec = scan.nextInt();
                                        case 0:
                                            kartAnaMenu();
                                            sec = scan.nextInt();
                                    }
                                    break;

                                case 2:
                                    String newKartNo;
                                    System.out.print("Yeni kart numarası:");
                                    newKartNo = scan.nextLine();
                                    customer.kartGuncelle(customer, newKartNo);
                                    System.out.println("Kart giriş menüsüne yönlendiriliyorsunuz");
                                    kartAnaMenu();
                                    sec = scan.nextInt();
                                    break;
                                case 3:
                                    customer.sil(customer.getHesapNo());
                                    System.out.println("Kart giriş menüsüne yönlendiriliyorsunuz");
                                    kartAnaMenu();
                                    sec = scan.nextInt();
                                    break;
                                case 4:
                                    kartlariGoster();
                                    System.out.println("Kart giriş menüsüne yönlendiriliyorsunuz");
                                    kartAnaMenu();
                                    sec = scan.nextInt();
                                    break;
                                case 0:
                                    girisMenu();
                                    sec = scan.nextInt();
                                    break;
                            }
                            break;
                        case 3:
                            eklemeMenu();
                            sec = scan.nextInt();
                            switch (sec) {
                                case 1:
                                    Hesap cst = new Hesap();
                                    cst.ekle(musteriHesapEkle(cst));
                                    System.out.println("Bankamıza hoş geldiniz.");
                                    break;
                                case 2:
                                    Kart cst2 = new Kart();
                                    cst2.ekle(musteriKartEkle(cst2));
                                    System.out.println("Bankamıza hoş geldiniz.");
                                    break;
                                case 0:
                                    girisMenu();
                                    sec = scan.nextInt();
                                    break;
                            }
                            break;
                        case 0:
                            System.out.print("Sistemden çıkış yapılıyor....");
                            break;
                    }
                }
                break;
            }
            case 0:
                System.out.print("Sistem kapatılıyor.");
                break;
        }
    } while (sec != 0);
}
}