package com.example.threading;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
/*
Threading:ma3neta bel 3arabe almaham
la7 ne7ke 3an mawdo3 mohem esmo threading 2aw le howe almaham 2aw eno keef ta3mel multithreading ya3ne ta3mel ta3adoud lal almahm fe l program

3ashen nefham mawdo3 l threading 5alena nshof sho heye l meshkle le wejhtne le 5aletne elja2 la mawdo3 l threading 2aw nesta5dem l threading

2abl ma nshof sho heye l meshkle fe ba3d l keyword lezm nefhama :
-process:heye 3ebara 3an 3amleye kbera bde5ela she esmo thread 2aw majmo3a mn l 3amlyet l z8era l heye l thread majmo3a mn almahem 2aw majmo3a mn l instruction le btetnafaz b maken wa7ad tab3an l process momken ykoun joweta thread wa7de 2aw aktar w l application nafso momken ykoun 3am yest3mel process wa7de 2aw akatr


hala2 badna nshof sho heye l meshkle le saret ma3e la 5aletne elja2 la mawdo3 l threading:
problem:ana 3mlt application b2labo button bel xml w 3mltelo inflate ba3den 3mlt onclick lisner bshakl eno iza bed8at 3lee bade nafez 3amlye mo3yane
kona mn zamen lama ned8at 3ala l button netba3 toast 2aw nafez aye 3mlye badna yeha w l 3amalye kona nla7ez eno ma bte5od wa2t tawel  de8re btetnfaz b ma3na eno bas etba3 toast de8re byetla3 toast 3ala shshe 2awl ma ekbous 3ala l button 2aw lama 2a3ml 3mlyt jam3 2aw darb 2aw aye 3mlye bade yeha  fa l 3malye hay ma betwel ma3ee
hala2 jowet l onclcik ta3et l button bade ektob code bade 5ale ye5od wa2t bel tanfez aktar mn  l wa2t le kona ne5do bl 3mlyet l abl heek b ma3na
law jeet jowa l onclick 3mlt for loop hay loop bada ta3mel loop mn l i=0 la i<10000000000 la i2a2l mn 100 malion lafe i++ hay for loop baseta jedan l for loop ana bas 7ateta b masebt code bye5od wa2t bel tanfez  akatr mn lezm momken ykoun badel hay l for loop code jalb data mn l database 2aw mn server bye5od wa2t 2aw ta7mel malaf bye5od wa2t etc.... mn hay l 3malyet
hala2 jowet hay l for loop bade 2a3ml system.out.println("khaled") 3ala ases eno wade7lak l 3amlye adesh 7a te5od wa2t ba3d ma saret tlof 100 malion lafe
3mlt run w w 3mlt click 3ala2 l button sar freez ya3ne ma fek tet8at 3lee mara teneye l button 2e3ed byetba3 esm khaled ya3ne eza shofna bel logcat l button 2e3ed byetba3 w be3le2 ma3e tab3an l 3malye hay l for lopp 7a dala tloof w temshe ma3e la3end ma y5les l 100 malion lafe tab3an zeer ba3do m3le2 law rje3t jarbt ted8at 3lee mesh 7a yestajeb 3meel freez iza tle3t mn l application la7 y3ale2 ma3e l application lesh l2no fe3lyan  l application msa5er kel l resource taba3to la7ata yrou7 ynafezle loop le 3ande w bas rje3t da8at 2akatar mn mara 3al button w 7ewalt etal3 mn l application 3mele l application box maktob 3lee l threads isn't responding w 3am y2olak tla3 mn l appliction l2no elo wa2t tawel benagez bel 3amlye w enta fe3layn ma 5alst l 3amalye  ya3ne l application keen byeshte8l bel 3amleye tab3te w ma 5alasa lisa

nerja3 la shar7 w nshof sho badna ne7na mn kel hay l ossa:
hala2 fe3layn  l application 2awal ma sha8lne ra7 l operating system ra7 3emel create la she esmo process l heye l 3amlye ba3den ra7 3emel de5el hay l process thread wa7de le heye ela mostala7 (main thread)
aye application mawjoud 3al telephone mojarad ma tsha8lo by diffult bedoun ma ta3mel aye she byenshe2 process wa7de bede5la thread wa7de le heye l (main thread) 2aw mostala7 tene (UI thread) 2aw user interface thread l2no hay l thread a8lab l 2awemer le btetnafaz feha 2awemer ela 3ela2a bel user interface metl l button ,textview,tasmem shshe kemleee etc....
fa fe3lyan l codedt tab3ntna le katabneha tnafazet bede5el l thread heda l main thread
w ne7na mna3ref nmn zamen eno  l codet l barmajehe btetnafaz bel tasalsol ya3ne wara ba3da ya3ne sequncial btenafaz satr satr ya3ne bel case 3ana ma benafez l code le ba3d l foor loop ela ma y5les l for loop fa fe3lyan ma 7a y5les l application w y5ale l button tab3tna available ela iza l for loop l 100 malion lafe le 5asa b jomlet teba3et esm khaled
fa heda l code :for (int i=0;i<1000000000;i++){ System.out.println("khaled"); } heda l code 5alale l application taba3e y3ale2 lesh?
l2no fe3layn l application taba3e ma keen fe 8eer thred wa7de le heye l main thred heye le nafazet l code fa fe3layn 3ashn ynafez l 100 malion lafe sa5ar kel l resources  le mawjoude bel main thread hay 3ashn t5ales l 100 malion lafe le mawjoude 3ande fa bhay l 7ale batalet l thread te2dar ta3mel 2aw testajeb la aye 3amlye tenye ana betloba ela lama t5ales l for looop l 100 mlaion lafe
fa ana fe3layn lama 7ewlt eda8at 3ala l button mara tenye ma stajble la da8tet l button l2no fe3lyan l thread 2e3de bteshte8le be loop  mesh fadye  fa ma adrane testajeb la aye she ana botlbo ela ma t5ales loop
fa ne7na le mnefhamo mn l kalem le 2elne eno l codet l baramjeye ta3wlete efteradeyan bteshte8l b thread wa7de l heye l main thread l thread hay btnafez l codet le mawjoude bel application sequntial be tasalsol be ma3na law nsha8let hay l thread be code mo3yan mesh la7 tkamel l codet l be2ye mesh la7 testjeb ele bnshakl 3am la aye she ela lama t5ales l sho8l le heye 2e3de bta3melo b2alba



tyeb sho l 7al:
l 7al eno mazel masmo7 ele eno ykoun fe bel application akatr mn thread bede5l l process fa 5als b5ale l thread l main thread metl ma heye l user interface thread w benshe2 thread tenye hay l thread tenye be ktob feha l codet le bade yeha le betwa2a3 eno tawel ma3e te5od wa2t fa le sar
eno ana b5ale l thread l 2ola l heye l main thread l ui thread le feha l 3anaser le btet3emal ma3 user interface metl l button textview..etc b5aleha l hay l thread ma ba3mel feha she b7es eno hay l thread la aye wa7d da8t 3ala zer 3mel aye 3mlye elo tkoun hay l thread mote7a elo
w benshe2 thread jdede w bektob b2lab l codet le betwa23 te5od wa2t fa hay l thread btesh8el nafsa tnafez l code le bte5od wa2t tawel
w ana bkoun heek 7alet l 2adeye

fa hala2 3ashn 7el l meshkle bade enshe2 thread jdede tyeb ma howe ana kent abl menshe2 thread 3ashn hala2 enshe2 thread jdede la2 tab3an bas metl ma 7kena abl shway eno l codet l b2lab l appliction btetnfaz demn l thread l 2asaseye l main thread l efteradeye le btkoun mawjoude  la7al 2awl ma nefta7 l application fa aye code bektbo bel  iza ma enta 7addt b2edak eno bado yeshte8l heda l code demn thread monfesle bseer la7alo 2aw efteradyn byeshte8l demn l thread l asaseyey l heye l main thread mn doun ma enta t7aded 2aw ta3mel she

tyeb ana momken 2a3ml thread jdeede akeed eh:
fe 3ande class bel java  esmo (Thread) mn 5elelo ana be2dar enshe2 thread jdede 2aw 3onsour bede5elo 2awemer barmajeye tetnafaz bshakl motaweze ma3 l thread l mawjoude 3ande ya3ne bshakl motaweze ma3 l main thread
fa b3ref thred object:Thread t1=Thread()
jowa l thread fe class interface esmo Runnable ba3mel mena new b2lab constructor l thread new Runnable(){ overide publice void run(){})} bas 2a3ml new Runnable btefta7 3ande meshod esma run(){}
hala2 enta btektob l code le mawjoud 3andak 2aw ya3ne le badak tetwa23 eno heda l code bada te5od menak wa2t tawel btektoba hay l codet bede5l method l run() ya3ne heda l code le howe l for loop le bya3mel 100 malion afe bjebo w bektobo b2lab l method l run()
hala2 l method le esma run bedala mo3ala2 ya3ne ma btetnafaz ela lama enta l programmer ysha8el l thread hay keef bado ysha8el mn 5elel method esma start bt7ot esm l object l 3mlto .start() .start() bt7ota bara l class l thread
so nerja3 n3eed 3ana class esmo thread  bede5lo interface esmo Runnbale  ba3mel mena new Runnable mobahsara l runnable bede5la method esma run() l run hay bektob feha l codet le betwa23 te5od wa2t tawel 2am bem3na tene  l codet le bade sha8ela bshakl shebeh motaweze ma3 l thread tab3te fa btseer hay l thread kola le 3mlta jdeed bteshkl motaweze ma3 l main thread l thred l 2slye  ui thread keef bado ysha8el mn 5elel method esma start bt7ot esm l object l 3mlto .start() .start() bt7ota bara l class l thread bhay tare2a bekou sha8ala lal thread w btseer btnafez l codet l b2laba
hala2 2awl ma 2a3ml run   l application mesh la7 y3le2 ma3e lama ed8at 3ala l button mobashara la7 ynafez l code l for loop b thread monfesle w 7a y5le l main thread user interface thread mote7a 3ande eno 2a3ml aye 3amlye bade yah ya3ne mesh 7a y3le2 ma3e ya3ne iza kabat l button mesh 7a y3le2 7a ydalo available w feek tet8at 3lee akatr mn mara w bte2dar tetla3 mn l application wala 7ada be2olak thread not responding wala she lesh l2no fe3lyan l application byeshte8l b shsheto btafaselo le mot3ale2a bel user interafce bel activity nafsa bteshte8l bel ui thread bel main thread  fa kawnak ro7t 3melt thread tenye w l code le bye5od wa2t tawel 7atyto b thread tenye  fa enta fe3layn l thread l 2aslye l main thread ui thread fadye w mesh mash8ole w jehze eno tehste8l aye sho8l badak yeh 3ashn heek sheft eno wa2t kabst l button dalo available l2no l main thread fadye ma kenet mash8ole fa de8re bas kabsto raj3t l button de8re availbale ya3ne de8re nafazet l code le b2labo l2no mana mash8ole
fa hay l fekra men eno na3mel thread jdede ftered eno l thread l jdede esma t1 w l main thread esma t2 l tnen hawde byeshte8lo bshakl shebh motaweze ma3 ba3d ya3ne paralel ya3ne t1 ma 2ela 3ele2a abadan b t2 t1 ,t2 bteshte8l l tenen ta2rebn byeshte8lo b nafs l wa2t
eno fe3layn mesh eno byeshte8lo b nafs l wa2t eno b nafs la7za eno ma byeshte8lo fe3lyan l tnen ma3 ba3d fe3layn bseer ta2sem lal thread btenmasak l thread w btet2sam la ajze2 haw l ajze2 eno instrucrion ya3ne l thread btet2sam la instruction z8ere w tenye kamen nafs she l main l thread fa bseer l process bsha8el jez2 mn l thread l jdede jez2 mn l main thread bseer heek jez2 mn hay jez2 mn hay jez2 mn hay jez2 mn hay ..etc
fa bel nesbe lal device mesh bshakl motaweze bas bel nesbe elna iza ne7na 3am nshof 7a nshofa bshakl motaweze l2no l 3amlyet l codet bteshte8l bshakl kteer sare3 bel mill second fa ne7na mn7esa eno bteshte8l bshakl motaweze
fa 3ashn heek ne7an 7alyna meshkletna  mn 5elel eno na3mel thread jdede w l thread hay nektob l codet l bte5od wa2t tawel b2lab 3ashn heek bteshte8l bshakl motaweze



UI thread 8alat eno 7ot feha code bte5od wa2t tawel w t2asser 3ala l thread eno t3atel sha8let l ela 3ela2a bel user interface fa lezm 7ota b thread tenye anshe2neha be2elola esm Worker thread
fa sar 3ande naw3en thread user interface thread wa7de  and Worker thread feek ta3mel mena ad ma badak 3adad mn l thread wa7de tnen tlete ad ma badak 3ala 7asab enta sho btesta5dem

tyeb hal heda l mawdo3 mafto7 ele ya3ne ana deymn be7ke eno deymn l thread btnafez bshakl shbh motaweze  fa l mante2 l baset l aye wa7ad momken yfaker fe eno aye code ana bade yeh ba3mlo b thread jdede fa b5ale l application asra3 ma yomken  !!!
houn 7ekelk la2 badak temsek l 3asaye mn l wasat la eno ta3mel thread wa7de wala eno tefta7a ta3mel threads kteeer lesh ?
l2no fe3layn   l main thread l code le feha ma bte2dar l thread l jdede tosala ya3ne ta3mel acces 3lyha la n2ol fe textview bara l thread l jdede w jena badna nemseka n8ayer l code taba3a mesh 7a ne2dar na3mel acces 3al textview l2no kel thread ela 3mlyet 5asa feha ya3ne l main thread feha codet barmajeye feha loop masaln mn 1 la 1000
l thread tenye l worker bada tetb3 kemet l i taba3 l loop l mawjoude bel main thread bas ma 7a tet2dar mobasharatan lesh  l2no l main thread codeta menfesle 3an l worker thread l jdede fa lezm 2a3ml communication 2aw tawasol been haw l threads 3ashen anafez l bayanet 2aw 2osal la baynet mawjude b threds monfsle bshakl l bede2e ba byenfa3 eno l threads tosal l baynet l mawjoude 3end ba3da fa 3ashnheek lezm koun 7ares w ana bet3emal ma3 mawdo3 l threads


fa bade erja3 l class l thread le 3mlto bade 2a3ml sha8le tenye bte5od wa2t kteer:
bde5el l class l thread 3ande method esma sleep()
Thread.Sleep():hay l method l sleep mn esma btnayem l thread le enta mawjoud feha ya3ne hay l method ween ma feek testd3eha ya3ne eza jeet bara l thread l jdede 7atytelo Thread.sleep()  w 7atytelo b2alba l wa2t le badak tnayem l thread bel milli second iza 7atytelo 5000 ya3ne Thread.Sleep(5000) ma3neta 7a ya3mel sleep 5000 milli second ya3ne 5 second thread.sleep(5000) btnayem l thread le heye mawjoude feha la modet 5000 sawene ween ma testd3eha 7a yen3mla estd3e2
hala2 lama katbt heda satr jowa l onclick la7ala l Thread.sleep(5000) 7a tnayem meen 5 sawene ?
7a tnyem l user interface thread 100 bel 100 lesh l2no heda l code  Thread.Sleep(5000) mawjoud demn l user interface thread l heye l efteradye 2aw l main thread
l Thread.Sleep() lezm ta3mla try w catch 3ashen bta3mel InterruptedExecption  fa e7teyat mna3mel try w catch 2aw throws
fa hal2 l Threads.sleep(5000) 7a twa2ef l bernemej l application la modet 5 sec
hala2 bas da8tna 3al button 3ala2 l application 3ala2et l button 5 sec w rej3et ba3d 5 sec shta8let tabe3e 5elel hal 5 sec l user interface thread kola 3ala2et fa fe wala sha8le bel user interface la7 testajeb elak 5elel fatret l Thread.sleep()
jareb d8at 3al button 3ala2 rja3 jareb d8at ma 7a yestjeeb ela ba3d 5 sec

hala2 rja3 jareb 3meel  thred jdede l worker thread w 7ot b2laba thrad.sleep(5000) 7a ya3mel sleep la modet 5 sawene bas l sleep 7a ykoun la l worker thread fa ma 7a twa2ef l button w ta3mel freez l2no code l thread.sleep mawjoud bede5l l thread l jdede l howe l worker thread
tyeb hal main thread la7 tet2sar bel mawdo3 akeed la2 l2no l thread.sleep mawjoude bede5l l thread l worker mesh l main thread fa l main thread 7a ydalo sha8al w dalel 3a hee kbous 3al button ma 7a t3le2 rja3 kbous 3al button mara tenye 7a dala available

howe fe3layn bas enta 3am ted8at 3ala l button ra7 sha8al thread jdede w taraka ma elo da5le bele b2alb l thread worker ya3ne law fe code tene ba3d l thread.start() la7 ykamlo tabe3e law aye 3mlye badak ta3mela ba3d heek feek ta3mela beshole l2no 7a ykamel mobsharatan ya3ne iza taba3t toast ba3d l t1.start() 3ade la7 yetba3o 7ata law mesh 5ales l 5 sec howe ma elo 3ela2a l main l thread bel new thread l worker thread howe 3ade 3am yeshte8l
ya3ne la7 ysha8el l main thread l worker thread w ykamel 3ade law feha wa7de waraha lal worker thread la7 ysha8ela kamen 3ade law fe toast 7a yetba3a bya3mel l badak yeh ya3ne law feha thread2 la7 yrou7 ysha8el thread 2 w yetreka law fe kamen ba3d thread 3 7a yrou7 ysha8ela w yetreka w 7a ykamel codo 3ade aye sha8le mawjoude ba3d heek 3ade benafeza be8ad nazar iza l new thread l worker thread 5alaset sho8la wala la2 fa hay l fekra mn l thred.slep
w kamen note eno iza kabsna 2awal mara l button 7a ynayem l new thread rja3 jareb de8re kbous l button mara tenye 7a yestjeb l button available bas mesh 7a ynayeb mara tenye l sleep la ye5laso l 5 sec ya3ne howe neyem hala2 rja3 kbous btenkebs l button bas mesh 7a yetnafaz she jowet l new thread la ye5laso l 5 sec




badna njareb sha8le bade rou7 jeeb textview w 7ota 3ala sheshe:
bade 2a3ml ba3den inflate lal textview
ba3den bade 2a3ml loop de5el l new thread (i=0;i<5;i++) loop btlef mn 0 la 4
hala2 jowa l for bade 2a3ml thread.sleep(1000) ya3ne seney  fe3layn l for hayde 7a tnafez l code l sleep kam mara 5ams marat ya3ne 7a ya3mel sleep 5 sec ba3d kel seneye bta3mel sleep bade 8ayer textview le 7atyto w 7ot textview.settext(i)
fe3layn sho 7a yseer 3ande bel 7ale hay?
2awl ma ed8at 3ala l button 7a yrou7 ysha8el l thread bel new thread sho 7a yseer
7a tlof loop mn i=0 la 5 la7 ya3mel 2awl mara wa2t tkoun l i=0 sleep la seneye ba3den mafroud ye5od kemet l =0  w y7ota bel textview  ya3ne ba3d senye 7a y5ale kemet l i=0 seneye tenye 7a y5aleha 1 seney 2 7a y5aleha 3 ba3den 4
bas le sar eno 2awal ma da8t 3ala l button sar expception w 3emel crush w tele3 mn l application
tyeb sho howe l expetion le tele3 ma3e alak:
Only the original thread that create the view (textview) herache can toach its view(textview)
sar nafs  kalem le 2olne abl shway eno l main thread ma btenfa3 tetwasal ma3 l new thread l worker thread w l worker thread ma byenfa3 yetwasal ma3 l main thread tyeb keef saret:

code l for loop w nzol jowa l thread l jdede l worker thread ana ro7t mn jowa l new thread l worker thread 2oltelo 8ayerle l mo7tawa le de5el l textview aywa ya3ne enta bte7ewel t3adel 3ala kemet 3onsour commponent mawjoud bel user interface thread ya3ne 3am t7ewel ta3mel acces la textview le howe mawjoud bel main thread mn l worker thread w heda mamno3

tyeb sho l 7al alak baseta fe kaza 7al :
alak hay l 7olol mote7a elak bas kel 7al elo tafaselo keef ya3ne alak ya3ne law badak tetwasal beeen 2 threads fe 3andak kaza tare2a:
1-RunOnUiThread:tare2a l 2ola 2alak fe method 3ana esma runonuithread hay tare2a btosta5dam iza enta mn  worker thread badak tnafez code b user interface thread(main thread)
ya3ne metl ma shofna ta8yer textview bel worker thread ma feek t8ayro lesh l2no code 2asesan mawjoud bede5l main thread fa lezm code ta8yer textview yetnafaz bel main thread momken wa7ad y2olak ana b8ayer textview barat l thread kolayan hay akabr jareme l2no ne7na ta8yer textview 7a ytenafaz abl l worker thread l2no howe l main thread sha8al  l work thread w kamal 3ade ya3ne nezel y8ayer textview w howe bekoun aslan bel wrker thread lisa ma feet asln 3al loop howe besha8l l thread w bekamel tabe3e ma 2elo 3ela2a aymta btblesh loop aymta bte5las aymta bya3mel sleep fa 7a ye2ra de8re l textview.settext ba3d tash8el l thread do8re fa enta keef btetba3 sha8le asln lisa ma 5alset 2emta ya3ne abl seneye l2ola ta3et l work thread sleep 7a ykoun textview.settext tnafazet fa lezm textview.settext ykoun mawjoud de8er l workthread bas ana bnafs l wa2t bade yeh yetnafaz bel user interface thread
ya3ne bade yeh ykoun bnafs l wa2t bel worker w l main thread

alak fe method esma runonuithread bte5od new runnbale ba3teha new runnable btefta7 3ade method esma run()
w b2lab run bektob l code le bade yeh le sar
eno l how 7a yemshe bel worker thread 7a yemshe b loop ya3mel sleep lala thread w basteje la jez2eyet l ta8yer textview le howe mawjoud textview bel main thread rou7 nafezle yeh heda l code bel mainnthread
runonuithread hay bte5od menak code bel run method b runnbale interface w btsha8el heda l code btnafzo bel userinterface thread le heye l main thread
bas ntebh eno abl ma t7et settext(i) badak tsayev value l in b variable final a3den t7ot badak tsayvo b variable i bara l runonuithread
heye l runonuithread ka2nak bterja3 bte72en l code le joweta de5el l ui thread
ka2nak 2oltelo bas t5ales l sleep rou7 3adele 3al textview bas 3adela ka2nak user interface thread
howe 7a ye5od l i=0 yerja3 ya3mel sleep lal thread ba3d seneye 7a ye5od emet l i=0 ysayeva b variable ba3den ya3mel onuithread w ysha8el l code l jowet l run bel main thread

tyeb hay tare2a iza 3ana tnen work thread w bade sha8el code mawjoud bel work thread l 2awl bade sha8lo b tenen?
la2 ma btezbta hay tare2a bas btezbat bel user interface thread ya3ne mn worker thread badak tsha8el code bel ui thread btezbat 8eer heek la2 ma btezbat mn worker thread badak tsha8el code b worker thread tenye beste5dem hay tare2a
hay tare2a bas iza badak tsha8el code mawjoud asln bel main thread mn 5elel l work thread 8eer heek hay are2a ma btemshe


2-Post|Postdelay:tare2a tenye la tsha8el code mawjoud bel asl bel main thread mn 5elel worker thread tash8el heda l code best3mel method esma post 2aw postdelay
-2alak badel ma test3mel runonuithread beje be5od 3onsour le mawjoud bel ui thread le howe textview
b2olo textview dot post textview.post() kamen bye5od runnable b2olo new runnable byefta7lk method esma run()
w jowet code l run b2olo textview.settext(finalI)
kamen houn badna n7ot l variable final

fa fe3layn byeshte8l heda l code le jowet run b2lab l main thread hay tare2a tare2t she esmo handler 7a neshra7 l handler ba3den howe byersel l code le howe textview.settext(finali) byerslo lal thread le mawjoud feha text view le howe l main thread l main thread mawjoud bede5ela l textview
howe byersel l code le mawjoud b2lab l run la 3onsour le 3mltelo dot ne7na 2olenelo textview dot post so byersel lal main thread l2no l main thread bede5ela 3onsour l textview

ya3ne hay l method bta3mel post lal code le mawjoud de5el run b 2alb l thread le mawjoud bde5lo 3onsor le 3mltelo dot post le howe textview (textview.post())

bas 5od belak l textview.post() hay 5asa bel user interface thread laken l handler bshakl 3am le fe joweta method l post w l postdelay bte2dar tersel bayent mn thread la thread mesh darore ykoun l main thread bas l post w l postdelay haw 5asen bas lal main thread bas l handler class le mawjoud bed5lo haw l methods bte2dar mn 5elelo tersel baynet mn thread la aye thread worker la worker ,worker la main la badak yeh
bas howe ka view (textview) 5asa bel ui thread bas .l2no textview 3atoul mawjoude bel ui thread 7a ne7ke ba3den 3ala l handler
fe nafso zeto bel zabt bas bekoun mn thread la aye thread


-Postdelay:l postdelay heye nafsa zeta l post bas bte5od parameter tene 8eer runnable le howe l millisec l time le howe delay le badak ta3mlo ya3ne 2olo 3000 millisec sho ya3ne:
ya3ne 3meel delay 3000 millisec 3 sec ba3den nafez l code le mawjoud de8er l postdelay le howe ta8yer l textview
ya3ne mesh hala2 ta3mel post lal code b2lab  thread le mawjoud fe l textview la 3meel post lal code  le mawjoud bel run() b2lab l ui thread  ba3d 3 sec
hala2 2awl ma da8tna 3al button 2awl she 7a ye5od 1 sec ba3den 7a yosal 3ala l post delay 7a nontor 3 sec ba3den 7at bel textview number 0 ba3den tele3 w 3mel loop la i=1 ba3den mnontor 1 sec  ba3den 1..etc2,3,4




-Handler:
tare2a l 3 la tawasoul been threads heye tare2et l handler
Handler:Class mawjoud 3ande bel android operating sysytem heda l class 3am w ashamal la tawasoul w la taresoul been l threads bshakl eno mesh lezm eno tkoun l thread le badak tetwasal ma3a tkoun main thread 2aw user interface thread metl runonuithread 2aw post,postdelay la2 mesh metlon fekret l handeler 2wsa3 been thread w aye thread bte2dar ta3mel taresoul
l fekra eno  fe jeha morsele w jeha mostkbel
l morsel byersel msg
w byest2bela l mostkbel w benafeza
hay bkel basata fekret ta3amoul ma3 class l handler ta3emoul ma3o basset kteer byeshbah kteer ta3amoul iza mnetzakar bel fragment lama kona nersel data mn fragment la activity 2aw l 3aks keef kona net3emal 3an tare2 listner 2aw heek she bas class l handler absat w fe methods b2albo jehze w 5alsa  kel le 3lye eno esta5dem hal methods este5dem
3ashen esta5dem class l handler
2awl she bade eje 3aref class inner class esmo masln MyHandler 3emel extends mn class l handler le mawjoud bel (os)
class l handler heda bade 2a3mel joweta overide le method esma handleMessage
method l handleMessage:3ebara 3an method btes2bel object mno3 Message mn aye wa7ad mn aye thread btersel l Message hayde w btet3emal ma3a fe3layn heda l class ween mawjoud mawjoud hala2 bel user interface thread bshakl 3am fene 5ale heda l class 3am 7oto static  ya3ne yseer aye wa7ad ye2dar yest5dmo mn esm l class 2aw 5ale heek be2sm l activity 7asab este5demo bel e5er
bas eno l fekra bel mawdo3 class l handler le ana 3mlto fe heda l class method esma handleMessage l handeleMessage hayde bte2dar test2bel feha l resele le badak yeha btoslk resele  ka parameter l resele mn no3 object esmo Message bte2dar bene2an 3lyha tjeeb bayneta w tet3emal ma3a
tab3an object l Message heda jehez mesh enta bta3mlo howe jehez
fe b2labo heda l object variable metl l atg1,ag2,what hawde no3oun integer w fe wa7ad esmo obj mno3 object,fe kamen bundle mn 5elel l getdata()
fa fe3lyan heda l class l message  bt2dar t3abe fe data w tersl mn 5elelo mot8ayerat bte2dar tersel meno objects bte2dar tersel meno bundle kemle bte2dar testa3mlo 3ashn tersel aye baynet badak yeha mn maken la maken hone 3mlo 3ashn yray7ok  bte2dar aye no3 mn data terslo mn thread la thread tenye hawde koloun l what,arg1..etc asme2 mot8ayerat houne 3mlolak yeha enta bt2dar t7ot feha l data le badak yeha ya3ne mesh keyam mn 3ando hay
jehet l most2bel jehze b2lab method l handlemessage heye le btest2bel data l morsle

tyeb keef bade et3emal ma3 jehet l morsel heda l class ana 3arfto tabe3e momken ykoun b matra7 monfasel 3ade beje bara ba3mel bel oncreate instance meno:MyHandler handler=new MyHandler();
tyeb ba3d ma 3mlt heda l class keef bade ersel l code le mawjoud 3ande le kont mamno3 2a3mlo bel thread howe textview.settext(i) keen mamno3 2a3mlo bel thread fa heda l code bade nafzo bel user interface thread wa7de mn toro2 bet3emel l handler
l handler ana be2dar ersel 3ala l method l handleMessage be2dar ersel code 2aw data keyam keef ya3ne?
ya3ne  enta momken tersel kemet l i la method l esma handlemessage le heye already bel user interface thread fa yer3rdlak yeha la7lo  la kemet l i b2lab l textview 2aw
tnafez mawjmo3a mn l  codes ma3 ba3d ya3ne tnafez code bas bel user interface ya3ne edemak 5ayaren:
ya btersel keyam w b2lab l handlemessage test2bela w bt7ota bel textview le howe heda l class mawjud de5el l main thread
2aw  btnafez l code b2alab l user interface mn jowet l worker thread

7a nshof l 2 cases:
1-keef bade ersel keme:beje 3ala l thread b2olo handler l object le 3mlto fo2 dot fe method esma sendMessage() hay l method bte5od menak object Message
fa b3aref object mn Message    Message message=new Message() w b3abe fe l data le bade yeh la heda l class object l message metl ma 7kena howe object 3am enta bt7aded bayento metl ma badak metl fekret l bundle
bta3be fe data metl ma badak exple:fe b2labo mo8yer esmo arg1 mn no3 integer fa bt2olo msg.args=i; fa 2eltelo l variable le esmo arg1 kemet l i 7a tkoun fe
w ba3den bel handler.sendMessage() b2olo rsele heda l object le 3mlto le esmo message handler.sendmessage(msg);
handler.sendmessage(msg);:2awl ma yestd3e satr heda 7a yrou7 yestd3e l method le esma handleMessage 3ashen yest2bel l object l msg le enta 3abyto ba3den 2a3rslto la method le esma send message w jowet l method le esma handle message be5od l keme mn object l msg le wesltne le btkoun bel parameter b method le esma handle message be5oda la object message be5od meno variable l arg1 w b7ota b textview

hala2 enta bel handle btest2bel l msg le wesltelk bel parameter w bt5eod mn l msg l arg1 w bt7ota b textview
heda l kalem le 3mlto kelo moshebeh kteer bele 3mlto bel post,postdelay,runonuithread

fa l fekra eno ana mn worker thread da8t 3ala l button fa fe3lyan nafzle 3mlyet erssel kemet l i mn l worker thread lal user interface thread lal method le esma handlemessage w a5ad w 7ata b textview

hay tare2a l 2ola b2este5dem method le heye sendMessage() 2arslt object kemel message 7atet feh l baynet le bade yeha

2alak fe function tene momken test5dme a esma handler.sendEmptyMessage() l empty msg hay bt2olak law enta ma 3andak msg metl 7aletna hay 3ande integer fa 2ostna mana me7teje rou7 2a3mel object kemel Message 3asshen 2eme wa7de le heye l i  2alak bel sendemptymsg fek directly t7ot senemptyMessage(i) tersela directly bala ma ta3mel object Message
w ba3den bel handleMessage kemet l i btosalak b object l msg le bel parameter b2lb varibale esmo what fa bta3mel textview.settext(msg.what)
ya3ne l 2eme le bt7ota bel sendemptymessage btosalak b variable b2lab l object l message esmo what
howe sendemptymessage 2asln bye5od integer esmo what  wl variable le esmo what le mawjoud de5el l msg howe naw3o integer fa enta bas ta3mel sendemptymessage le 2eme le 3am t7ota 3am tetsayv b variable l what w bel handle message 3am njeba la 2emet l what le wesltne mn object l message
senemptymessage a7san elo lal performance l2no enta ma b7aje tersel object enta bas 3am teb3at keme wa7de fa ma fe de3e lal tersele object
bas enta aymta bte7tej tersel keme lama ykoun 3andak aktar mn keme badak tersela fa btest3mel sendmessage sa3eta

-fekamen ba3d method teb3en la hawde l e mara2o :

-sendEmptyMessageAttime():ma3net bade ersel keme wa7de menno3integer btest2bal b mot8ayer what ,tene parametr bte5od time bel millisec bade 7aded l wa2t le bade sha8el 3mlyet l ersel fe ya3ne 5000 millsec maln fa ya3ne aymta badak tersel kemet l i hayde lal method le esma handle message b wa2t mo3ayan hay best5dema lama ykoun 3ande wa2t mo7adad wa2t mn server ana ma bad2yn bade est3mel l wa2t taba3 system SystemClock.uptimeMills()+5000
ya3ne bade ersel kemet l i la method l handle message bel wa2t l system ze2ed 5 sawene ya3ne ba3d 5ams sawene bteshbh delay bel 7ale hay bas hala2 ma adranen nest5dema b mesel eno ykoun fe wa2t mo3yan sebet metl heek method byest3mel wa2t jeye mn database,server..etc
bas 3mlna run 2a5d 2emet l i w ba3d 5ams sawene 3ard l 0,1,2,3,4 bas 2awl mara howe


-sendEmptyMessageDelayed():ma3neta bade ersel keme wa7de meno3 integer btestbel b mot8ayer what ,tene parameter bte5od time dellayed bel millisec manln 5000 ya3ne kel mara bas yeb3at l msg 7a ya3mel delay 5000 sec ba3den yeb3ata 3ala l handle message w y7ota b textview


-sendMessageAttime()|sendMessageDelayd():hawde nafsoun zetoun senemptymessageattime,senemptymessagedelay bas houn ana beb3at object mn message  mesh keme wa7de





2-
hala2 law bade nafez code:
handler.post():nafs l code l bel post w post delay
handler.post():bte5od new runnable btefta7 function esma run w bebtel lezmbel handler message t7ot code textview.settetxt() bseer l code le btektbo jowet l function l run() l code le btektobo jowet l function run byetnafaz jowet class l handler le howe mawjoud class l handler b2lab l uithread
hay nafs l code le mawjoud bel code l textview.post() ,view.post() ya3ne bas hedek kenet mo5asasa bas lal ui thread hay l handler.posst() btnafez code b thread le mawjoud fe class l handler

-postattime():
hay nafs l handler.post() bas btnafez code m3yan b2ab l thread l mawjoud fe class l handler b nafez l code b wa2t m3yan ana ba3te yeh

-postDelayed():
hay nafs l handler.post() bas btnafez code m3yan b2ab l thread l mawjoud fe class l handler b nafez l code bas bya3mel delay masln 5000 5 sawene enta bta7ded ade ya3ne nafez heda l code bas ba3d wa2t m3yan ana ba3te yeh



bel mo5tasar eno l method l esma post w kel le mot3ale2a feha postdelayed 2aw post at time hay 3ashen ana nafez code b thread l mawjoud feha class l handler
ama send message w sendemptymessage w kel she byetba3oun lama ana bade ersel keyam 2aw data la thread l mawjoud feh l handler w byest2bel hay l keyam b function handle mesage w bene2an 3lyha bektob l code bel ui thread 2aw bel thread le mawjoud feha class l handler






hala2 bade shof keef fene 2a3ml aktar mn 3amlye 2aw est2bel aktar mn 3amleye
hala2 bade 5ale l btn tsha8el l thread bas best5dem l handler
w bnafs l wa2t l handler yraj3le message lal method l handle message
ya3ne bade 5ale l thread l mawjoude 3ande be5oda kola metl ma heye w tale3a bara l button 7ota bara
bas 5aleha final 3ashn 2e2dar 2osal la emeta jowet l clicklistner l ananymous class click

w jowet l btn sha8lt l thread
bade sha8ela beste5dem l handler
ya3ne jowet l btn 2oltelo:
handler.sendemptymessage(-1):w 3atyto bel parameter -1
2olelo jowet l btn hayda sendemptymessage -1
w jeet lama sta2blt l message 2olelo:
iza l messgae kenet btsewe -1 rou7 sha8ele l thread
if(msg.what==-1){
thread.start()
}


sho le sar:
sha8lt l thread 3an tare2 l handler ba3den 2arslt lal message kemet -1
ba3den 2oleo iza weslak -1 sha8el l thread
else
law welak she 8eer l -1
7ota lal eme le 7a tosalak b textview
textview.settext(msg.what)


bas ana lesh 3mlt hay l 7arake hay l 7arake bas 3ashn nshof keef momken eno 2e2dar:
2a3ml akatr mn 3amleye ersel w tnawe3 b 3amlyet l este2bel

hone l mot8ayer le esmo what logically 3emlo 3ashn heek eshay  3ashn enta tersel no3 l 3amlye
momken tkoun no3 l 3maleye  edafe ta3del tash8el thread...etc

ya3ne be2dar 7asab nou3 l message nafez code momken law kenet object message 2aye she tene 2a3ml shart nafez code m3ayan

w momken eno ersel mn akatr mn manta2a data
 */



















public class ThreadAndHandler extends AppCompatActivity {

    Button button;
    TextView textView;
    Thread thread;
    Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.btn);
        textView=findViewById(R.id.txt);



        MyHandler handler=new MyHandler();

         thread=new Thread(new Runnable() {
            @Override
            public void run() {

                for(int i=0;i<5;i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    final int finalI=i;
                   handler.sendEmptyMessage(finalI);
                }
            }
        });

        button.setOnClickListener(v->{

//            Thread thread=new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    for (int i=0;i<1000000000;i++){
//                        System.out.println("khaled");
//                    }
//                }
//            });
//            thread.start();

//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            Thread thread=new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Thread.sleep(5000);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            });
//            thread.start();
//
//        });
//            Thread thread=new Thread(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        for (int i = 0; i < 5; i++) {
//                            try {
//                                Thread.sleep(1000);
//                            } catch (InterruptedException e) {
//                                throw new RuntimeException(e);
//                            }
//                            final int finlI=i;
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    textView.setText(String.valueOf(finlI));
//
//                                }
//                            });

//                            textView.post(new Runnable() {
//                                @Override
//                                public void run() {
//                                    textView.setText(String.valueOf(finlI));
//                                }
//                            });

//                            textView.postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//                                    textView.setText(String.valueOf(finlI));
//                                }
//                            },3000);
//                            Message msg=new Message();
//                            msg.arg1=finlI;
//                            handler.sendMessage(msg);

//                            handler.sendEmptyMessage(finlI);

//                               handler.sendEmptyMessageAtTime(finlI, SystemClock.uptimeMillis()+finlI*5000);
//                            handler.sendEmptyMessageDelayed(finlI,finlI*5000);

//                            handler.post(new Runnable() {
//                                @Override
//                                public void run() {
//                                    textView.setText(String.valueOf(finlI));
//                                }
//                            });
//                        }
//                    }
//                });
//            thread.start();
//
//
            handler.sendEmptyMessage(-1);
        });



    }

    class MyHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            //textView.setText(msg.arg1);
//            textView.setText(msg.what);

//            textView.setText(String.valueOf(msg.what));
            if(msg.what==-1){
                thread.start();
            }else{
                textView.setText(String.valueOf(msg.what));
            }



        }
    }
}