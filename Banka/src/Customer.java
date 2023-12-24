public class Customer//Müşteri bilgilerinin tutulduğu sınıf 
{
    private String name;
    private String surname;
    private String TC;
    private String telefonNo;
    Customer(){}
    protected void setName(String name)
    {
        if(sayiKontrol(name))
        {
            this.name=name;
        }
        else
        {
            System.out.print("a");
        }
    }
    protected String getName()
    {
        return this.name;
    }
    protected void setSurname(String surname)
    {
        if(sayiKontrol(surname))
        {
            this.surname=surname;
        }
        else
        {
            System.out.println("Girilen isim değerini kontrol ediniz");
        }
        
    }
    protected String getSurname()
    {
        return this.surname;
    }
    protected void setTC(String TC)
    {
        if(TCKontrol(TC))
        {
            this.TC=TC;
        }
        else
        {
            System.out.println("Girilen TC değerini kontrol ediniz!!!");
        }
    }
    protected String getTC()
    {
        return this.TC;
    }
    protected void setTelefonNo(String telefonNo)
    {
        if(telefonNo.length()==10&&sayiKontrol(telefonNo)==false)
        {
            this.telefonNo=telefonNo;
        }
        else
        {
            System.out.println("Girilen telefon numarasini kontrol ediniz!!!");
        }
    }
    protected String getTelefonNo()
    {
        return this.telefonNo;
    }   
    private boolean TCKontrol(String TC)//Klavyeden girilecek olan TC'nin 11 karakterde ve rakamlardan oluştuğunu kontrol eden fonksiyon
    {
        boolean sonuc=true;
        if(TC.length()==11)
        {
            int i;
            for(i=0;i<TC.length();i++)
            {   //geçici bir temp değeri tanımlayıp o TCdeki karakterleri tektek alıp 0 ile 9 arasında veya eşitmi diye ascii de kontrol ettim.
               String temp;
                temp=""+TC.charAt(i);
                
                if(temp.matches("[0-9]")==true)
                {
                    sonuc=true;
                }
                else
                {
                        sonuc=false;
                }
            }
        }
        else
        {
            sonuc=false;
        }
        return sonuc;
    }
    protected boolean sayiKontrol(String karakter)// klavyeden girilen isim veya soyisimde sayi varmı yok mu diye kontrol edecek olan fonksiyon. 
    {
       boolean sonuc=true;
       int i;
       
       for(i=0;i<karakter.length();i++)
       {
           String temp=""+karakter.charAt(i);
           if(temp.matches("[0-9]+")==true)
           {
               sonuc=false;
           }
          
       }
       return sonuc;
    }
}