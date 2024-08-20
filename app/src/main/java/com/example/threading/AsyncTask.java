package com.example.threading;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/*
hala2 ne7na 7kena abl 3an l ui thread w l worker thread w shofna l 4 sha8let l mawjoude runuithread,post,postdelayed,handler le mn5eleloun mna3mel 3amlyet  tawasoul been l thread l 2ola w l thread tenye 2aw aye 2 threads mawjoude sawe2 kenet uithread 2aw worker thread hay l 4 sha8let besta5dema la tawasoul 2aw 3ashen sha8el code mn worker thread fe user interface thread

hala2 l sha8let l mawjoude 3ande hala2 le bade balesh feha le heye Thread class, AsyncTask,Executor hawde 3ebara 3an toro2 enshe2 threads jdede b ma3na ana ma bade est5dem l user interface thread bade 2a3mel thread 5asa fene l thread hay benshe2a  b toro2 hay:
Thread class:tare2a l 2ola best5edem class le esmo Thread l class l efterade le sta5dmne abl heek le kona na3mel Thread thread=new Thread() w 7atyna joweto runnabe w 3mlnelo thread.start()  hay 3ebara 3an absat tare2a le enshe2 thread jdede bel java

AsyncTask:tare2a tenye tare2a afdal mn thread class best5dem class esmo AsyncTask w hay 3ebara 3an tare2a sahle kteer w tare2a 3am w ashmal mn tare2a l 2ola bas ne7na 3ashen nefham keef badna nest5dema w lesh badna nest5dema bade 7ewel hala2 tabe2 fekra mo3yane best5dem Thread class w shof ween momken 2o2a3 b meshkle beste5dem Thread class w 7a netar ba3d heek eno nelja2 la mawdo3 le howe l AsyncTask w eno est5dmo bshe8le
hala2 bade 2a3ml button b nous shshe esmo download w faw2a fe progressbar w fou2 l progressbar fe textview bte3redle feha  nesbet ta2adoum l progress bar ya3ne le 7a yseer bas ekbous kabset download bade 5ale ya3mel download la file mn l internet l malaf heda bye3redlak ta2adoum taba3 ta7mel l file bel progress bar w bnafs l wa2t bye3rdlak b2alab textview nesbet l ta2doum mn 100 ya3ne 20% 50% heek tab3an ana ma bade 2a3mel ta7mel la file bade etemal ma3 l thread.sleep le t3melat ma3a abl heek
bade 2a3mlo eno bas ekbous 3al button bade l progress kel seneye yet2adam 10 step
hala2 b2akb l button onclick ba3mel Thread using Thread class lesh l2no l 3amleye le bade 2a3mela sawe2 tanzel file 2aw thread.sleep hay 3amleye bte5od wa2t tawel fa ma bade est3mela de5el l ui thread fa bade rou7 2a3mel thread jdede using Thread class
2awl ma balesh l app lezm l progress w l textview me5feyen bade 2awl ma ekbous 3ala l button bade ezher l progress bar w textview hone efteradeyan me5feyen
ba3den ba3mel thread w jowet run ba3mel loop mn i=0 la i=10 w bade eje kel mara 2olo 3mele thread.sleeep la modet senye wa7de kel mara 2awal mara benyema wa2t i=0 senye byerja3 2abl ma tseer 1 bnyema senye  heekk lal i=9
fa fe3lyan 7a tlof loop 10 marat w kel mara 7a ta3mel sleep lal thread 10 sawene
hala2 kel mara bade nayem l thread seney bade mashe l progress 10 step keef ya3ne ya3ne bafroud ba3d ma y5les senye progressbar.increamentprogressby(10) ya3ne zeed l progress 10 5etwet
w ba3d ma tzedle yeh 3adele l textview textview.settext(progress.getprogrss+"/"+"100") ya3ne kel mara badak tjeeb l 2eme mn l progress w t7etele yeha b textview
hala2 hawde satren mafroud ween nafezoun:
progressBar.incrementProgressBy(10);
textView.setText(progressBar.getProgress()+"/100");

lezm nafezoun bel ui thread l2no textview w l progress bar mawjouden bel ui thread fa ma fena nosaloun fa mnefta7 runonuithread le bt5lek tnafez code mawjoud de5el l worker thread bt5lek tnafzo de5el l ui thread fa mn 7ot haw satren de5el l runonuithread de5el function on run():
progressBar.incrementProgressBy(10);
textView.setText(progressBar.getProgress()+"/100");
w bade bas 5ales 3al e5er e5fe l download w textview fa bade 7ot barat loop kamen runonuithread w 7ot l progress w textview gone

fe3layn l code ta3ele shata8al 100 bel 100 bas 5abst 3al button bayano l textview w progress w sar kel seneye yzeed l progress bar 10 step w l textview yzeed 10 w bas sar 100 bye5fele l textview w l progressbar
bas 2alak ta3a la 2olak sha8le ya3ne enta bhay tare2a kel ma badak tosal la 3onsour bel ui thread badaak ta3mel runonuithread w ta3mel runnable jdede 2aw t3mel handler 2aw post w heek fa kel mara badak tosal la 3osnour badak test5dem wa7de mn haw fa eja alak fe she a7san w a7la w absat mn ta3emoul ma3 Thread class le ma bt5lene 2osal la 3onsour bel ui thread ela iza 3mlt wa7de mn toro2 2alak fe class esmo AsyncTask heed l class mo5asas la ta3amoul been l user interface thread w l worker thread w tana2ol baynetoun besehole  esmo l class AsyncTask


hala2 keef badak tet3emal ma3 l AsyncTask:
bta3mel bara class jowa l activity bara l oncreate bade 3aref class bade same MyTask
class l AsyncTask howe class fe b2albo majmo3et function mo5asasa eno tet3emal ma3 l worker thread w bnafs l  wa2t ma3 l ui thread bma3na:
eno l thread l 3adeye le heye l worker thread le t3mlna ma3a keen bas runnable heye l method l wa7ede le fene nafez codete feha fa l code le mawjoud feha byetnafaz bel worker thread ma byetnafaz bel ui thread fa lezm 3ashn nafez code b thread l ui thread est3mel runonuithread bas l aysnctask la2 alak fe functions mo5asasa la tana2ol been tenten fe jez2 byeshte8l bel work thread w jez2 byeshte8l bel ui thread  b2alb heda l class  bel asynctask tnafezle code bel ui thread bala l runonuithread
fa hala2 ba3mel class esmo MyTask extend la class esmo AsyncTask w feha generic type btem ta7dedo keef ya3ne generic type no3 le btem ta7dedo ben < >  howe generic type houn 3ashn y7aded no3 l function le bada tkoun jowa l class l AsyncTask heda 2olna b2albo functions l functions hawde bade 2a3mela overide l functions  hayde return ta3ouleta  w l parameter ta3oula le be7aded anwa3oun la return w l parameter houne generic type le mawjouden 3ande 3ashn heek samohoun generic type anawa3 dynamic  ya3ne btet8ayar bene2an 3ala no3 le enta btektbo ya3ne 7asab no3 le btektobo bekoun l function byest3mlo

fe 3ande 3 generic type:
<Params>:params ma3enta no3 l parameter taba3 function esma doinbackground()
<Progress>:progress ma3neta no3 l parameter taba3 function l progress
<Result>:result no3 l return le bada traj3o l asynctask hay
akede ma fehmena she 5aelene tabe2a ba3den nefham
2awl she l generic type l params bade 7oto String
l progress bade 5ale no3a Integer
l result bade 5aleha Void
ba3den 2alak lezm ta3mel immplement la method 2alak 3al 2alele lezm ta3eml immplement la method wa7de esma doinbackground
doinbackground():hayde l method ejbare ta3mela immplement l wa7ede le btetnafaz bel worker thread bte5od parameter mo3yane le heye ta3et l params w hyde l function ma ela return l2no return howe void w l void ma elo return hyda l code le b2laba byetnafaz bel worker thread
ya3ne l class heda b7alto hay : class MyTask extends android.os.AsyncTask<String,Integer,Void> {

        @Override
        protected Void doInBackground(String... strings) {
            return null;
        }
    }


ka2no 3emel thread metl l fo2 mndoun l runonuithread

doinbackground():l code le bade ektbo feha la7 tet2akad eno la7 yetnafaz bel worker thread howe fe3lyan de5lyan l asynctask ma bya3mel thread wa7de bya3mel aktar mn thread  bya3mel she metl l poll ya3ne benfez l 2ola ba3den tenye ba3den telte  ya3ne byam3le metl l que bshkl eno enta law nafzt task 3 4 marat wara ba3d l 3 4 marat hawde befoto b queu  w bseer be5lesoun wa7de wa7de
ya3ne ma fe n2ol eno tameman nafs l thread  laken mne3tebro bshaklo l 5areje ma dem l code le mnektobo b function l doinbackground howe 3ebara 3an code byetnafaz bel worker thread laken bshakl asese bshakl 2areb kteer byeshbah l thread le 3mlne 2awl she le fe l code le byetnafaz bas bel worker thread laken l AscyncTask bseer fe jadwale b queu

hala l code le bade ektobo bel doinbackground howe l code le bado yenkatab 2aw yetnafaz bel worker thread fa bjeebl code le kont ketbo bel Thread class ta3el le byetnafaz bas bel worker thread le howe mn l forloop bas abl bade etala3 3ala parmeters ta3el function l doinbackground():
hayde l function l parameter sho naw3a String w 3 no2at fa l no3 l 2awl l params howe no3 l parameter taba3 l function l doinbackground
ya3ne lama testd3e l function l doinbackground mn bara heda l class sho l parameter le badak tersela ela momken enta badak tersel rabet l malaf le badak enta  ta3melo download momken badak tersel esm l file le badak t7amlo momken badak tersel esm jadwal le badak tjeeb meno data mn database aye she badak tersela mn bara 3ala l function doinbackground  bt7ota bel params
tyeb sho ya3ne String w 3 no2at String...:tlat no2at ya3ne l parameter le btfout 3ala l function generic type  sho ya3ne:

t5ayal 3andak function sum 3adne btrj3lak jam3 ra2men ok ya3ne publc int sum(int x.int y){return x+y} of w re7t stad3yta 3mlt sum(10,20) btredlak 30 ama iza 3mlt sum(10) w ma 7atet tene paremeter bta3melk erro sho 3ela2et hay bel bade e7ke
enta bel sum momken t7aded l parameter int... ya3ne public int sum(int...x){}bta3ref sho ma3neto hay ma3neto eno l mod5al taba3 function sum 3adada 3ebara 3an 0 2aw aktar mn l keyam le momken yda5ela l most5dem ya3ne 3adad l mod5alaet generic mota3dade
ya3ne yemken l user yda5el wala keme yemken yda5el 10 keyam 20 keme ad ma badak ya3ne law l most5dem ma da5al wala keme 3ade law l most5dem da5al keme wa7de 3ade law da5al 20 keme 3ade
tyb keef byet3emal ma3a jowa byet3emal ma3a ka masfofe array ya3ne hyade l keyam btosal ka array
ya3ne btosalk l x heye 3ebra 3an array fa  momken law badak ta3mel 3amlyet jam3a bta3mel public int sum(int...x){

int result=0;
for(int i=0;i<x.length;i++{
result+=x[i]
}
return result;

bas heda no3 generic ma feek t3arfo ka varibale ya3ne heek int ...x la2 ma fek
heda shakl bas byest5dam ka mod5al lal functions 3ashen t2olo eno l parameter le badak da5lo hounn 3ebara 3an generic mot3aded

}

fa bel function l doinbackground nafs l fekra 7atetlak String...string ya3ne l keme le bada tosal houn 3ebra 3an arraylist fa momken teb3at enta keme wa7de 2aw ad ma badak


-tyeb sho fe function teneye bel Asyctask:

2-onPreExecute():ma3neta abl l tanfez sho badak ta3mel

3-onPostExecute(Void avoid):ba3d tanfez sho badak ta3mel

4-onProgressUpdate(Integer...integr):5elel ta2dom bel tanfez sho badak ta3mel

5-oncanceled():bas t5les sho badak ta3mel

onPreExecute(),onPostExecute(Void avoid),onProgressUpdate(Integer...integr),oncanceled():haw kel l function byetnafazo bel ui threads


bas l doinbackground(String...string):btetnafaz bel worker threads




hala2 5alene tabe2 l mesel le 3mlto fo2 ta3el l button mn 5elel kel haw l methods:
2awl she 3andak jez2 le howe sleep bas lal thread heda l code bado yeshhte8l bas bel worker thred so mn7oto de5el l donbackground lesh l2no fe3lyan 3ebra 3an code byetnafaz bas bel worker thread

-3mleyet ezhar 5ale l progress w l textview ybyno visible hyada bten3amal bel ui thread w 2abl l code taba3 l worker thread fa bade 7ot heda l code de5el l onpreexecute() heye l 3mlye keef btseer 2awl she byestd3e l preexecute,doinbackground,onpostexecute(),
w l onprogressupdate hayde btestd3e bel wasat 7asab enta b2edak std3yta wala la2 metl ma 7a nshof ba3d shway

-hala2 ba3d ma 5ales l 3amlye ta3et l worker thread bade 5ale l 3anser tseer gone fa bade 7ot l code de5el l onpostexecute

-tyeb 5elel tanfez l 3amlye mafroud ana zeed l progress b 10 w l tetview 7ot feha l progress fa hea bado yetnafaz bel ui thread w 5elel l 3amleye fa heda l code b7oto bel onprogressupdate b2olo kol mara 3mele increamnt b 10 w 7ot l keme w 3abele yeha bel textview


bas lesh ana 7atet parameter integr w hal 5abreyet masln fene ana hayde l increament by 10 l 10 eb3ata ana w test2ball bel onprogressupdate w ten7at b2lab function l increamentprogresssby() 7a ne7ke 3an hal she

ana hala2 l methods saro jehzen 3ande bas mesh mest5demoun ana bade 2olo kel mara btlof feha lafe b loop kel lafe badak testd3e l onprogress 3ashn tnafez l code le joweto l2no iza ma 2eltelo heek sa3eta ba3d ma y5les l doinbackground 7a yrou7 3ala l progress sa3eta ma 7a nestfed badna kel lafe abl ma y5les doinbackground kemle ba3d kel lafe yestd3e l onprogress la y3adel l progreesbar w textview


keef bade 2a3ml hal she :
fe 3ande method esma publishProgress():hay l method betro7 btestd3e l onprogressupdate fa hay l method bestd3eha b2alb l do inbackground sa3eta ba3d kel loop 7a testd3a l onprogressupdate l2no ne7na metl ma 2olna l onprogressupdate ma btestd3a ela yadaweyan l publish progress bte5od paramter metl l onprogress update fa fene ana eb3t mn l publishprogress integer w hayde l integr btosal lal onprogressupdate w bt3teha lal l increamenetprogressby
fa ba3mel publishProgress(10) w bel onprogressupdate best2bel hay l 10 ba3mel progress.inreamentprogressby(values[0]) w feek teb3at aktr mn keme b2lab l publishprogress(Integer...)
ne7na iza mn shof metl l application snaptube enta bas tnazel btshof 3andak fe progress bas kel wa7ad la 7alo m2atesh msha2af la kaza wa7de hayde keef saret 3ando akat mn progressbar w bel publishprogress kel mara 3am yeb3at aktar mn keme w 3am yest2bel bel onprogressupdate lal keyam w y7ota bel progress



hala2 keef bade sha8lo b2lab l click listner ba3mel object mn class l Mytask:Mytask mystask=newMystask()
mytask.excute():excute btnafezle l methods bas la7ez eno l excute bte5od mn parameter mno3 String generic tyeb lesh? l2no hay l method fe3lyan betro7 btestd3e method l doinbackground w bteb3at l keyam le bt7ota bel excute la function l doinbackground w bel doinackground feek test2bel l vaiebles

be2dar b3den estd3e le bade masln mytask.iscancelled() la 2a3rf hal l 3mle l doinbackground n3mala cancel
2aw fene ana 2a3ml cancel lal doinbackground 7otelo mytask.canceled()

l Asynctask howe 3ando majmo3et threads bas ta3mel call la excute bejblak wa7de mn haw l thread w bnafez code le mawjoud b do in background b thread heda le jebo howe 3ando pool of thread bjeeb wa7ad menon w bnfez code le mawjoud bel do in background fe w be2e l methods menfezoun bel ui thread

note:iza mnla7ez l return value ta3el method do inbackground bteje ka parameter 3ala method l postexecute ya3ne k2no 3am y2olak ba3d method do inbackground bestd3e method l onpost w l return value byeje 3ala l postexcute feek test2blo w ta3mel fe le badak yeh



ExecutorService:
l2no l AsyncTask depricator mnest3mel Executor sevice
howe 3ebra 3an pool of worker thread
Fa mn3ref variable mn l Exector service
la 2a3mel worker thread best3mel method  Executors.newSingleThreadExecutor() hayde l method bta3melak single worker thread w bt7tot b2lab variable esmo ExecuterService
ba3den la ta3mel task b2lab heda l thread btest3mel method esma execute

ba3den la ta3mel method b2lab l ui thread bta3mel object mn Handler handler=new Handler() hayde l handler bte5od looper
kel thread 3anda looper ana bade 7ot b2lab l handler l ui looper iza 7atyto b2lab l handler fa kel l method le 7a tnafeza mn 5elel heda l handler handler.post,handlersend message hay keloun 7a yetnafazo bel main thread
fa la tjeeb l mai looper taba3 l main thread fe method esma  Looper.getMainLooper() hayde ba3teha lal handler object bel constructor
ba3den ba3mel lal executeseview.exute bte5od runnable hayde btefta7kak method run() b2laba bt7ot l eshya le badak tnafeza b2lab l worker thread
ba3den la tnafez task b2lab l ui thread bta3mel handler.post()

ExecutorsService b2labo kteer thread ma7toten b2alb thread pool
le bya3mel mangment la heda l thread pool class Executors ya3ne howe ne7na 3teber 3ana majmo3et task 5 task w majmo3et thread 4 thread kel thread mestelme task tyeb l 5ames meen byestelmo l thread le bt5les sho8la 2awl she heda she8l l Executors class

tyeb 3ande kaza tyeb of executors:

1-SingleThreadExecutor():fekrto eno nafez worker thread wa7ad bel process
2-FixedThreadPool(int n):fekrto eno enta btenshe2 3ada m3yan mn l thread bene2an 3ala number of n le bt7ota bas 3atoul lezm n7ot l n heye 7asab number of core bel cpu ya3ne iza cpu number of 5 fa mn7ot l n =5 l2no kel thread byeshte8l b core wa7ad iza number of thread keen akbar mn 3adad l cores fa number of thread bsero yetala3o t5ayal ana 7atet 3ande 10 thread fa 7a ynafezle 10 sha8let ma3 ba3d tyeb w ana 3ande 20 task fa howe 7a ynafezzle 2awl 10 w tene 10 task 7a y7etele yehoun b queue w bseer kel ma fedyet wa7de thread btestelm l thread le edyet bte5od hay task
3-chashedThreadPool():howe enta bel 3ade ma bt3ref kam thread bedak ya3ne ma fe ra2m m3yan lal thread ya3ne iza eja 4 task 4 thread heek  iza keen 3ande kel l thread mash8olen 3am ya3mlo task m3yan w task jdede ejet l pool create new thread to excute heda task iza heda l thread l jdeede dalo idle la modet 60 sec then it removed from pool
aymta esta3mel chasedthreadpool:b2olak ma test5dema ela iza met2aked 100 bel 100 task le la7 tet5ol heye 3ebra 3an task 3adad ma7dod jedan ya3ne 3eref eno number of task le la7 tejene maln 3 5eyaro 7a ykoun momtez

4-ScheduledExecutor():fekrto ba3mel thread bade yeha b wa2t m3yan ya3ne bade thread mo3yane teshte8l masln kel  30 min w trou7 malsn ta3mel loop 3ala databse trou7 tshof iza fe paymnet malsn mo3yane pending trou7 t5aleha commplete hay fekreta


note:
Looper.getMainLooper():btjeeb looper le mawjoud bel main thread le benafez task howe looper le byest2bel message w byeb3at message
Looper.myLooper():btjeeb looper le  taba3 l thread le ma7al ma 3mlna itilization lal handler b2lab heda l thread ya3ne iza 3mlne b2lab thread m3yan btjeble looper taba3 heda l thred le m3yan ama iza 3mlne bl main thread itilize la handler btejble l looper taba3 l main thread















*/












public class AsyncTask<S, I extends Number, V> extends AppCompatActivity {

    ProgressBar progressBar;
    TextView textView;
    Button download;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        progressBar=findViewById(R.id.progressBar);
        textView=findViewById(R.id.text_progress);
        download=findViewById(R.id.download);

        download.setOnClickListener(v->{
//            textView.setVisibility(View.VISIBLE);
//            progressBar.setVisibility(View.VISIBLE);
//            Thread thread=new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    for(int i=0;i<10;i++){
//                        try {
//                            Thread.sleep(1000);
//                        } catch (InterruptedException e) {
//                            throw new RuntimeException(e);
//                        }
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                progressBar.incrementProgressBy(10);
//                                textView.setText(progressBar.getProgress()+"/100");
//                            }
//                        });
//
//                    }
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            textView.setVisibility(View.GONE);
//                            progressBar.setVisibility(View.GONE);
//
//                        }
//                    });
//                }
//            });
//            thread.start();

            //MyTask myTask=new MyTask();
            //myTask.execute();

            ExecutorService executors=Executors.newSingleThreadExecutor();
//           ExecutorServices This is an interface in Java that represents a thread pool, which is a collection of worker threads that can be used to execute tasks concurrently
//           Executors.newSingleThreadExecutor() This is a factory method that creates an ExecutorService with a single worker thread. This means that tasks submitted to this executor will be executed sequentially, one after another, on the same thread in workerthread
            Handler handler=new Handler(Looper.getMainLooper());
//          Looper.getMainLooper() This method returns the Looper associated with the main thread (also known as the UI thread). The main thread is responsible for handling UI updates,
            //l eshya le bade 2a3mela abl l doinbackground le heye pre ba3mela houn
            textView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);

            //halal2 la 2a3mel l task le b2lab l doinbackground b2alb method l execute bta3mel task b2lab l worker thread
            //w bas 2a3mel handler.post() l eshya le b2lab btetnafaz bel main thread l2no Looper.getmainLooper btjeblak l mainthread fa kel she 7a ta3mel post mn 5elel hay l handler 7a yetnafaz bel mainthread

            executors.execute(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);

                        }

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.incrementProgressBy(10);
                                textView.setText(progressBar.getProgress()+"/100");
                            }
                        });

                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            textView.setVisibility(View.GONE);
                            progressBar.setVisibility(View.GONE);
                        }
                    });

                }
            });




        });



    }

    class MyTask extends android.os.AsyncTask<String,Integer,Void> {

        @Override
        protected void onPreExecute() {
            textView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... strings) {
            for(int i=0;i<10;i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                publishProgress(10);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.incrementProgressBy(values[0]);
            textView.setText(progressBar.getProgress()+"/100");
            super.onProgressUpdate(values);
        }


        @Override
        protected void onPostExecute(Void unused) {
            textView.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            super.onPostExecute(unused);
        }


        @Override
        protected void onCancelled() {
            super.onCancelled();
        }




    }

  


}