import java.io.*;
import java.util.Scanner;
public class Admin//Bankacı sistem giriş sınıfı
{
    private String adminId;//7 adet sayı dizisinden olusan bankacinin sisteme girişi için zorunlu olan değişken
    private String password;
    private File file;
    public Admin()//program calışırken admin sınıfının kullanacı bilgilerinin barındığı dosyayı açan constructor
    {
        file=new File("admin.txt");
    }
    protected String getAdminId()
    {
        return this.adminId;
    }
    protected String getPassword()
    {
        return this.password;
    }
    protected void setAdminId(String adminId)
    {
        if(idKontrol(adminId))
            this.adminId=adminId;
    }
    protected void setPassword(String password)
    {
        this.password=password;
    }
    private boolean idKontrol(String adminId)//ID 'nin 7 adet sayidan oluşup oluşmadığını kontrol eden fonksiyon
    {
        boolean sonuc=true;
        String temp;
        if(adminId.length()==7)
        {
            for(int i=0;i<adminId.length();i++)
            {
                temp=""+adminId.charAt(i);
                if(temp.matches("[0-9]+"))
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
    protected void ayracKaldir(String adminBilgileri)//Dosyadan alınan bilgileri kullanıcıId ve password'e eşitleyen fonksiyon
    {
       String[] temp=new String[2];
       for(int i=0;i<temp.length;i++)
       {
           temp[i]="";
       }
       int z=0;
       for(int i=0;i<adminBilgileri.length();i++)
       {
           String temp2=""+adminBilgileri.charAt(i);
           if(";".equals(temp2)==false)
           {
               temp[z]+=temp2;
           }
           else
           {
               z++;
           }
       }
       setAdminId(temp[0]);
       setPassword(temp[1]);
    }
    protected boolean giris(Admin bankaci)throws IOException//Mainde oluşan bankaci nesnesinden alınan bilgilerinin doğruluğunun kontrol edildiği fonksiyon
    {   boolean sonuc=true;
        try
        {
            Scanner reader=new Scanner(file);//dosyadaki bilgileri okuyan değişken
            String str;//dosyadaki bilgilerin 
            while(reader.hasNextLine())//dosyada satır olduğu süre boyunca okuma yapma
            {   str=reader.nextLine();
                ayracKaldir(str);//dosyadan okunan bilgilerin alındığı ve sınıftaki değerlere atama yeri
                String idTutucu=getAdminId();//ayrac kaldırdan aldığı adminID değerini tutan değişken 
                String passwordTutucu=getPassword();//ayrac kaldırdan aldığı adminID değerini tutan değişken
                sonuc = idTutucu.equals(bankaci.getAdminId())&&passwordTutucu.equals(bankaci.getPassword());//Admin sınıfından gelen değerlerin olup olmamasına bağlı olarak dönen değer
            }
            reader.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return sonuc;
    }
}
