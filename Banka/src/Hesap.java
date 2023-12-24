 import java.io.*;
import java.util.Scanner;
public class Hesap extends Customer 
{
   protected File file;
   private String hesapNo;//16 karakter
   private String subeNo;//4 karakter
   private String bakiye;
   protected Hesap()
    {
       this.file = new File("hesap.txt");
    }
  protected void setHesapNo(String hesapNo)
  {
      if(super.sayiKontrol(hesapNo)==false&&hesapNo.length()==16)
      {
          this.hesapNo=hesapNo;
      }
      else
      {
          System.out.println("Hesap numaranizi kontrol ediniz!!!");
      }
  } 
  protected void setSubeNo(String subeNo)
  {
      if(super.sayiKontrol(subeNo)==false)
      {
          if(subeNo.length()==4)
          {
              this.subeNo=subeNo;
          }
      }
  } 
  protected void setBakiye(String bakiye)
    {
        if(bakiyeKontrol(bakiye))
        {
            this.bakiye=bakiye;
        }
        else
        {
            System.out.println("Girilen bakiye değerini kontrol ediniz!!");
        }
    }
  protected String getHesapNo()
  {
      return this.hesapNo;
  }
 
  protected String getSubeNo()
  {
      return this.subeNo;
  }
 
    protected String getBakiye()
    {
        return this.bakiye;
    }
     protected boolean bakiyeKontrol(String bakiye)//Klavyeden eklenecek olan para değerinin içinde sayı ve . varsa doğru değeri döndürecek fonksiyom
    {
        
        boolean sonuc=true;
        for(int i=0;i<bakiye.length();i++)
        {
           String temp=""+bakiye.charAt(i);
           if(temp.matches("[0-9]")||".".equals(temp))
           {
               sonuc=true;
           }
           else
           {
               sonuc=false;
           }
        }
        return sonuc;
    }
    protected void ayracKaldir(String hesapBilgisi)//Dosyadan okunmuş olan hesap bilgilerinin dizi elemanları yardımyla setlendiği fonksiyon
    {
        String[] temp=new String[7];
        for(int i=0;i<temp.length;i++)
        {
            temp[i]="";
        }
        int z=0;
        String temp2="";
        for(int j=0;j<hesapBilgisi.length();j++)
        {
           temp2=""+hesapBilgisi.charAt(j);
           if(";".equals(temp2))
           {
               z++;
           }
           else
           {
               temp[z]+=temp2;
           }
        }
        super.setName(temp[0]);
        super.setSurname(temp[1]);
        super.setTC(temp[2]);
        super.setTelefonNo(temp[3]);
        setSubeNo(temp[4]);
        setHesapNo(temp[5]);
        setBakiye(temp[6]);
    }
    protected String hesapNoDondur(String hesapBilgileri)//dosyadan aktarılmış olan satırdan hesapNo değerini döndürme
    {
        String[] temp=new String[7];
        for(int i=0;i<temp.length;i++)
        {
            temp[i]="";
        }
        int z=0;
        String temp2="";
        for(int j=0;j<hesapBilgileri.length();j++)
        {
           temp2=""+hesapBilgileri.charAt(j);
           if(";".equals(temp2))
           {
               z++;
           }
           else
           {
               temp[z]+=temp2;
           }
        }
        return temp[5];
    }
    protected String tcNoDondur(String hesapBilgileri)//dosyadan aktarılmış olan satırdan TC değerini döndüren fonksiyon
    {
         String[] temp=new String[7];
        for(int i=0;i<temp.length;i++)
        {
            temp[i]="";
        }
        int z=0;
        String temp2="";
        for(int j=0;j<hesapBilgileri.length();j++)
        {
           temp2=""+hesapBilgileri.charAt(j);
           if(";".equals(temp2))
           {
               z++;
           }
           else
           {
               temp[z]+=temp2;
           }
        }
        return temp[2];
    }
    protected void hesaplariListele()throws IOException,FileNotFoundException //dosyadan satır satır okuma yapıp bunları önce ayracKaldira atip hesap değerlerini alan sonra print'le bu değerleri yazdıran fonksiyon
    {
            Scanner reader = new Scanner(file);//hesap.txt'te için okuyucu oluşturuldu
            String hesapBilgileri;
            while(reader.hasNextLine())//hesap.txt 'de satır olduğu sürece okuyucu nesnesi okumaya devam edecek
            {
                hesapBilgileri=reader.nextLine();
                ayracKaldir(hesapBilgileri);
                print();
            }
            reader.close();
    }
     protected void print()//Hesap ve customer sınıfı değişkenlerini ekrana basan fonksiyon
    {
        System.out.println("Ad:"+super.getName());
        System.out.println("Soyad:"+super.getSurname());
        System.out.println("TC:"+super.getTC());
        System.out.println("Telefon Numarasi:"+super.getTelefonNo());
        System.out.println("Sube Numarasi:"+getSubeNo());
        System.out.println("Hesap Numarasi:"+getHesapNo());
        System.out.println("Bakiye:"+getBakiye());
    }
      protected void ekle(Hesap newCustomer)throws IOException//Dosyaya hesap ekleme işlemi yapan fonksiyon
    {   
        String temp=newCustomer.getHesapNo();
        String temp2=newCustomer.getTC();
        boolean sonuc=sorgula(temp,temp2);//dosya müsterinin önceden olup olmadığını kontrol etme
        if(sonuc==false)
        {
            String ayrac=";";
            String hesapBilgileri="";
            //dosyaya aktarilicak olan tüm bilgilerinin toplandığı yer
            hesapBilgileri=hesapBilgileri.concat(newCustomer.getName()+ayrac+newCustomer.getSurname()+ayrac+newCustomer.getTC()+ayrac+newCustomer.getTelefonNo()+ayrac
                            +newCustomer.getSubeNo()+ayrac+newCustomer.getHesapNo()+ayrac+newCustomer.getBakiye());
            BufferedWriter bWriter=new BufferedWriter(new FileWriter(file,true));
            PrintWriter writer=new PrintWriter(bWriter);//dosyaya işlemi için kullanılacak olan değişken 
            writer.println(hesapBilgileri);//dosyadaki satira yazma işlemi yapan değişken
            writer.flush();
            writer.close();
            bWriter.close();
        }
        else
        {
            System.out.println("Musteri yeniden dosyaya eklenemez!!!");
        }
       
      }
      protected void hesapGuncelle(Hesap customer,String yeniHesapNo)throws IOException//ekle ve sil fonksiyonlari yardimiyla güncelleme yapan fonksiyon
      {
              String str;
              String temp =customer.getHesapNo();//eski hesapNo'yu tutan değişken
              String temp2=customer.getTC();//Eski TC'yi tutan değişken
                  if(sorgula(temp,temp2))
                  {
                      customer.setHesapNo(yeniHesapNo);//yeni hesapNo'sunu müşterinin aldığı yer
                  }
              ekle(customer);//Müşteri dosyaya yeniden eklendi
              sil(temp);//eski hesapNo'sunu tutan satır sil fonksiyonuyla dosyadan kaldırıldı
      }
       protected void sil(String hesapNo)//hesapNo yardımıyla müsteriyi dosyadan silen fonksiyon
    { 
        //Bilgilerin geçici olarak yazılacağı dosya adi ve dosya
       String tempFile="temp.txt";
       File newFile=new File(tempFile);
       String str;
       
       try
       {
           BufferedWriter bWriter=new BufferedWriter(new FileWriter(tempFile,true));//geçici dosyaya ekleme yapacağımız için true dedik
           PrintWriter writer=new PrintWriter(bWriter);//BufferedWriterı PrintWriter'a ekledim
           BufferedReader reader=new BufferedReader(new FileReader(file));//eski dosya için reader oluşturdum
           while((str=reader.readLine())!=null)//Eski dosyada satır olduğu sürece bilgileri reader aktaracak olan döngü
           {
               String temp=str;
               ayracKaldir(str);//satırdaki değerlerin o anki sınıf değişkenlerine eşitlendiği yer
               str=getHesapNo();
               if(str.equals(hesapNo)==false)//eğer satırda silinmesi istenen değişken yoksa satırın yeni dosyaya yazdırıldığı kısım
               {
                   writer.println(temp);
               }
           }
           writer.flush();
           writer.close();
           bWriter.close();
           reader.close();
           file.delete();//Eski dosya silindi
           newFile.renameTo(file);//Yeni dosya eski dosyanın yerini aldı
       }
       catch(IOException e)
       {
           e.printStackTrace();
       }
        
    }
       protected boolean sorgula(String hesapNo,String TC)throws IOException//Müsterinin dosyada bulunup bulunmama durumuna göre true yada false dödüren fonksiyon
       {
           boolean sonuc=true;
           Scanner reader=new Scanner(file);
           String hesapBilgileri;
           while(reader.hasNextLine())
           {
               hesapBilgileri=reader.nextLine();
               String holder=hesapNoDondur(hesapBilgileri);
               String holder2=tcNoDondur(hesapBilgileri);
               for(int i=0;i<hesapNo.length();i++)
               {
                  
                  String a=""+holder.charAt(i);
                  String b=""+hesapNo.charAt(i);
                 if(a.equals(b))
                  {
                      sonuc=true;
                  }
                  else
                  {
                      sonuc=false;
                      break;
                  }
               }
               for(int j=0;j<TC.length();j++)
               {
                  String a=""+holder2.charAt(j);
                  String b=""+TC.charAt(j);
                  if(a.equals(b))
                  {
                      sonuc=true;
                  }
                  else
                  {
                      sonuc=false;
                      break;
                  }
               }
           }
           reader.close();
           return sonuc;
       }
       protected void paraTransferi(Hesap gonderen,Hesap alici,String gonderilecekMiktar)//paraCekme ve paraYatirma fonksiyonları yardımıyla hesaplar arası para geçişini sağlayan fonksiyon
       {
            try
           {
              if(sorgula(gonderen.getHesapNo(),gonderen.getTC())&&sorgula(alici.getHesapNo(),alici.getTC()))
              {
                  paraCekme(gonderen,gonderilecekMiktar);//Parayi gönderecek olan hesaptan ceken fonksiyon 
                  paraYatirma(alici,gonderilecekMiktar);//Parayı yatacak olan hesaptan alan fonksiyon
              }
           }
           catch(IOException e)
           {
               e.printStackTrace();
           }
       }
       protected void paraGuncelle(Hesap customer,String yeniBakiye)throws IOException//değişen para değerlerini hesap dosyasından bulup değiştiren fonksiyon
       {  
           if(sorgula(customer.getHesapNo(),customer.getTC()))
           {
               //hesapNo sunu değiştirip dosyaya para miktarını güncelleyip hesapGuncelleme fonkisiyonu yardımıyla eski hesapNo'suna döndürerek bakiye güncelleme yapma
                String orgHesapNo=customer.getHesapNo();
                String fakeHesapNo="1234567890123456";
                customer.setBakiye(yeniBakiye);
                customer.setHesapNo(fakeHesapNo);
                ekle(customer);
                sil(orgHesapNo);
                hesapGuncelle(customer,orgHesapNo);
           }
       }
       protected void paraCekme(Hesap customer,String cekilecekPara)//Hesaptaki para değerini ve cekilecek para miktarını alıp Double değişkenine çevirerek para çekme işlemini yapan fonksiyon 
       {
           try
           {
               Double hesaptakiPara=0.0;
               Double alinacakPara=Double.parseDouble(cekilecekPara);
               if(sorgula(customer.getHesapNo(),customer.getTC()))
               {
                    hesaptakiPara=Double.parseDouble(customer.getBakiye());
               }
               if(hesaptakiPara>=alinacakPara)
               {
                   hesaptakiPara-=alinacakPara;
               }
               String temp=hesaptakiPara+"";
               paraGuncelle(customer,temp);
               
           }
           catch(IOException e)
           {
               e.printStackTrace();
           }
       }
       protected void paraYatirma(Hesap customer,String yatirilacakMiktar)//Hesaptaki para değerini ve yatirilacak olan para miktarını alıp Double değişkenine çevirerek para yatirma işlemini yapan fonksiyon 
       {
           try
           {
               Double yatirilacakPara=Double.parseDouble(yatirilacakMiktar);
               Double hesaptakiPara = null;
               if(sorgula(customer.getHesapNo(),customer.getTC()))
               {
                   hesaptakiPara=Double.parseDouble(customer.getBakiye());
                   hesaptakiPara+=yatirilacakPara;
                   String temp=hesaptakiPara+"";
                   paraGuncelle(customer,temp);
               }
           }
           catch(IOException e)
           {
               e.printStackTrace();
           }
       }
       protected void hesapBilgileriAlma(String hesapNo,String tcNo) throws IOException//İşlem yapmak istenen hesap nesnesinin bilgilerinin dosyadan çekildeği fonksiyon
       {
           boolean sonuc=false;
           if(sorgula(hesapNo,tcNo))
           {
               
            Scanner reader=new Scanner(file);
            String hesapBilgileri;
            String temp ="";
           while(reader.hasNextLine())
           {
               hesapBilgileri=reader.nextLine();
               String holder=hesapNoDondur(hesapBilgileri);//o satırdaki hesapNo ya eşitlendi.
                    
               for(int i=0;i<hesapNo.length();i++)
               {
                  String a=""+holder.charAt(i);
                  String b=""+hesapNo.charAt(i);
                  if(a.equals(b))
                  {
                      sonuc=true;
                  }
                  else
                  {
                      sonuc=false;
                      break;
                  }
                 
               }
                 if(sonuc)
               {
                   temp=hesapBilgileri;
                   ayracKaldir(temp);
               }
           }
           reader.close();
           }
       }
}