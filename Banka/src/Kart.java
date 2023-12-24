import java.util.Scanner;
import java.io.*;
public class Kart extends Hesap {
    private String kartNo;//16 haneden oluşur
    private String cvc;
    protected Kart()
    {
        this.file=new File("kart.txt");
    }
    protected void setKartNo(String kartNo)
    {
        if(super.sayiKontrol(kartNo)==false&&kartNo.length()==16)
        {
            this.kartNo=kartNo;
        }
    } 
    protected void setCVC(String cvc)
    {
        if(cvc.length()==3&&super.sayiKontrol(cvc)==false)
        {
            this.cvc=cvc;
        }
    }
    protected String getKartNo()
    {
        return this.kartNo;
    }
    protected String getCVC()
    {
       return this.cvc;
    }
     protected void ayracKaldir(String kartBilgisi)//Dosyadan okunmuş olan kart bilgilerinin dizi lelemanları yardımıyla setlendiği fonksiyon
    {
        String[] temp=new String[9];
        for(int i=0;i<temp.length;i++)
        {
            temp[i]="";
        }
        int z=0;
        for(int j=0;j<kartBilgisi.length();j++)
        {
            String temp2=""+kartBilgisi.charAt(j);
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
        super.setSubeNo(temp[4]);
        super.setHesapNo(temp[5]);
        setKartNo(temp[6]);
        setCVC(temp[7]);
        super.setBakiye(temp[8]);
    } 
      protected String kartNoDondur(String kartBilgileri)//Dosyadan okunmuş olan satırdaki kartNo 'sunu diziden alıp dönüştüren fonksiyon
    {
        String[] temp=new String[9];
        for(int i=0;i<temp.length;i++)
        {
            temp[i]="";
        }
        int z=0;
        String temp2="";
        for(int j=0;j<kartBilgileri.length();j++)
        {
           temp2=""+kartBilgileri.charAt(j);
           if(";".equals(temp2))
           {
               z++;
           }
           else
           {
               temp[z]+=temp2;
           }
        }
        System.out.println(temp[6]);
        return temp[6];
    }
        protected String tcNoDondur(String kartBilgileri)//Dosyadan okunmuş olan satırdaki TC'sini diziden alıp dönüştüren fonksiyon
    {
         String[] temp=new String[9];
        for(int i=0;i<temp.length;i++)
        {
            temp[i]="";
        }
        int z=0;
        String temp2="";
        for(int j=0;j<kartBilgileri.length();j++)
        {
           temp2=""+kartBilgileri.charAt(j);
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
    protected void kartlariListele()throws IOException,FileNotFoundException //dosyadan satır satır okuma yapıp bunları önce ayracKaldira atip kart değerlerini alan sonra print'le değerleri yazdıran fonksiyon
    {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String kartBilgileri;
            while((kartBilgileri=reader.readLine())!=null)
            {
                ayracKaldir(kartBilgileri);
                print();
            }
            reader.close();
    }
     protected void print()//Kart,Hesap ve customer sınıfı değişkenlerini ekrana basan fonksiyon
     {
        System.out.println("Ad:"+super.getName());
        System.out.println("Soyad:"+super.getSurname());
        System.out.println("TC:"+super.getTC());
        System.out.println("Telefon Numarasi:"+super.getTelefonNo());
        System.out.println("Sube Numarasi:"+super.getSubeNo());
        System.out.println("Hesap Numarasi:"+super.getHesapNo());
        System.out.println("Kart Numarasi:"+getKartNo());
        System.out.println("CVC:"+getCVC());
        System.out.println("Bakiye:"+super.getBakiye());
       
     }
     protected void sil(String kartNo)//kartNo yardımıyla müsteriyi dosyadan silen fonksiyon
     {
       String tempFile="temp.txt";
       File newFile=new File(tempFile);
       String str;
       
       try
       {
           BufferedWriter bWriter=new BufferedWriter(new FileWriter(tempFile,true));//dosyaya ekleme yapacağımız için true dedidl
           PrintWriter writer=new PrintWriter(bWriter);//BuffereadReaderı PrintWriter'a ekledim
           BufferedReader reader=new BufferedReader(new FileReader(file));//eski dosya için reader oluşturdum
           while((str=reader.readLine())!=null)
           {
               String temp=str;
               ayracKaldir(str);
               str=getKartNo();
               if(str.equals(kartNo)==false)
               {
                   writer.println(temp);
               }
           }
           writer.flush();
           writer.close();
           bWriter.close();
           reader.close();
           file.delete();
           newFile.renameTo(file);
           
       }
       catch(IOException e)
       {
           e.printStackTrace();
       }
     }
     protected boolean sorgula(String kartNo,String TC)throws IOException//gönderilen kart no ve TCNo dosyada var mı diye kontrol eden fonksiyon
       {
           boolean sonuc=true;
           Scanner reader=new Scanner(file);
           String kartBilgileri;
           while(reader.hasNextLine())
           {
                kartBilgileri=reader.nextLine();
                String holder=kartNoDondur(kartBilgileri);
                String holder2=tcNoDondur(kartBilgileri);
               for(int i=0;i<kartNo.length();i++)
               {
                  
                  String a=""+holder.charAt(i);
                  String b=""+kartNo.charAt(i);
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
     protected void ekle(Kart newCustomer)throws IOException//önceden tanımlamaları tek tek alım yaptığında kullanılması için
    {
       
        String temp=newCustomer.getKartNo();
        String temp2=newCustomer.getTC();
        boolean sonuc=sorgula(temp,temp2);
        if(sonuc==false)
        {
            String ayrac=";";
            String kartBilgileri="";
            kartBilgileri=kartBilgileri.concat(newCustomer.getName()+ayrac+newCustomer.getSurname()+ayrac+newCustomer.getTC()+ayrac+newCustomer.getTelefonNo()+ayrac
                            +newCustomer.getSubeNo()+ayrac+newCustomer.getHesapNo()+ayrac+newCustomer.getKartNo()+ayrac+newCustomer.getCVC()+ayrac+newCustomer.getBakiye());
            BufferedWriter bWriter=new BufferedWriter(new FileWriter(file,true));
            PrintWriter writer=new PrintWriter(bWriter);
            writer.println(kartBilgileri);
            writer.flush();
            writer.close();
            bWriter.close();
           
        }
        else
        {
            
            System.out.println("Musteri yeniden dosyaya eklenemez!!!");
        }
       
      }
       protected void kartGuncelle(Kart customer,String yeniKartNo)//güncellenmek istenen Kart sınıfına ait nesneyi ve yeni kartNo'sunu alarak değiştirme işlemi yapacak olan fonksiyon
       {
           try
           {
                String str;
                String temp =customer.getKartNo();
                String temp2=customer.getTC();
                    if(sorgula(temp,temp2))
                      {
                        customer.setKartNo(yeniKartNo);
                      }
                   ekle(customer);
                   sil(temp);
           }
           catch(IOException e)
           {
               e.printStackTrace();
           }
       }
       protected void paraCekme(Kart customer,String cekilecekPara)//Hesaptaki para değerini ve cekilecek para miktarını alıp Double değişkenine çevirerek para çekme işlemini yapan fonksiyon 
       {
           try
           {
               Double karttakiPara=0.0;
               Double alinacakPara=Double.parseDouble(cekilecekPara);
               if(sorgula(customer.getKartNo(),customer.getTC()))
               {
                      karttakiPara=Double.parseDouble(customer.getBakiye());
               }
               if(karttakiPara>=alinacakPara)
               {
                   karttakiPara-=alinacakPara;
               }
               String temp=karttakiPara+"";
               paraGuncelle(customer,temp);
               
           }
           catch(IOException e)
           {
               e.printStackTrace();
           }
       }
       protected void paraYatirma(Kart customer,String yatirilacakMiktar)//karttaki para değerini ve yatirilacak olan para miktarını alıp Double değişkenine çevirerek para yatirma işlemini yapan fonksiyon 
       {
           try
           {
               Double yatirilacakPara=Double.parseDouble(yatirilacakMiktar);
               Double karttakiPara = null;
               if(sorgula(customer.getHesapNo(),customer.getTC()))
               {
                   karttakiPara=Double.parseDouble(customer.getBakiye());
                   karttakiPara+=yatirilacakPara;
                   String temp=karttakiPara+"";
                   paraGuncelle(customer,temp);
               }
           }
           catch(IOException e)
           {
               e.printStackTrace();
           }
       }
       protected void kartBilgileriAlma(String kartNo,String TC) throws IOException//İşlem yapmak istenen kart nesnesinin bilgilerinin dosyadan çekildeği fonksiyon
        {
           boolean sonuc=false;
           if(sorgula(kartNo,TC))
           {  
            Scanner reader=new Scanner(file);
            String kartBilgileri;
            String temp ="";
           while(reader.hasNextLine())
           {
               kartBilgileri=reader.nextLine();
               temp=kartBilgileri;//satırında bulunan kartla ilgili bilgiyi aldı
               String holder=kartNoDondur(kartBilgileri);//o satırdaki hesapNo ya eşitlendi.
                    
               for(int i=0;i<kartNo.length();i++)
               {
                  String a=""+holder.charAt(i);
                  String b=""+kartNo.charAt(i);
                  sonuc=a.equals(b);
               }
                if(sonuc)
               {
                   temp=kartBilgileri;
                   ayracKaldir(temp);
               }
           }
           reader.close();
           }
       }
}