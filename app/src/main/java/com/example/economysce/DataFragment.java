package com.example.economysce;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.economysce.Adapters.UserAdapter;
import com.example.economysce.Class.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataFragment extends Fragment {
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<User> userList;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data,container,false);
        this.userList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        readUsers();
        return view;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void readUsers(){
        AddUsers();
        userAdapter = new UserAdapter(getContext(), userList);
        recyclerView.setAdapter(userAdapter);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void AddUsers(){
        userList.clear();
        userList.add(new User("1","חיים","גלוקמן ","M",new Date(1985,10,9),new Date(2013,6,10),new Double(12520.828125),new Date(2013,6,10),100,new Double(0),null,""));
        userList.add(new User("2","שרה","אברגל ","F",new Date(1985,9,23),new Date(2014,1,10),new Double(32100.046875),new Date(2014,1,10),72,new Double(0),null,""));
        userList.add(new User("3","מאיר","טרבלסי","M",new Date(1966,2,17),new Date(1990,9,10),new Double(34897.078125),null,0,new Double(464928.75),new Date(2021,6,1),"התפטרות"));
        userList.add(new User("4","אירית","אשכנזי","F",new Date(1967,9,6),new Date(2002,1,7),new Double(16716.375),null,0,new Double(118345.5),null,""));
        userList.add(new User("5","נתנאל","קיופמן","M",new Date(1967,9,28),new Date(2000,3,1),new Double(18814.1484375),new Date(2000,3,1),100,new Double(0),new Date(2021,10,1),"התפטרות"));
        userList.add(new User("6","משה","קופרמן","M",new Date(1968,9,20),new Date(1992,8,25),new Double(23708.953125),null,0,new Double(253597.5),new Date(2021,7,15),"פיטורין"));
        userList.add(new User("7","חיה ","לוי","F",new Date(1968,8,1),new Date(2015,7,3),new Double(35830.7038476562),new Date(2015,7,3),100,new Double(0),null,""));
        userList.add(new User("8","צילה","ברנבוים","F",new Date(1969,8,13),new Date(1994,2,1),new Double(16210.11234375),null,0,new Double(219784.5),new Date(2021,8,7),"פיטורין"));
        userList.add(new User("9","אביגל","מצטנר","F",new Date(1973,3,28),new Date(2001,5,30),new Double(17695.3359375),new Date(2001,5,30),100,new Double(0),null,""));
        userList.add(new User("10","אבי","גולדמן","M",new Date(1973,9,11),new Date(2016,8,21),new Double(14618.6015625),new Date(2016,8,21),72,new Double(0),null,""));
        userList.add(new User("11","משה","אבולפיה","M",new Date(1973,9,1),new Date(2002,3,1),new Double(34897.078125),null,0,new Double(321223.5),null,""));
        userList.add(new User("12","אלקס","פלטניקוב","M",new Date(1973,10,17),new Date(2016,7,20),new Double(11821.5703125),new Date(2016,7,20),100,new Double(0),null,""));
        userList.add(new User("13","מיכאל","גולד","M",new Date(1973,12,27),new Date(2000,1,11),new Double(36994.8515625),new Date(2019,9,7),100,new Double(211331.25),null,""));
        userList.add(new User("14","מרים","אדלשטיין","F",new Date(1973,11,22),new Date(2001,12,1),new Double(36855),null,0,new Double(185971.5),new Date(2021,4,3),"התפטרות"));
        userList.add(new User("15","עדי","וקסמן","M",new Date(1970,10,15),new Date(2011,2,11),new Double(48043.125),new Date(2011,2,11),72,new Double(0),null,""));
        userList.add(new User("16","אסנת","גולדברג","F",new Date(1972,1,4),new Date(2000,12,28),new Double(15317.859375),new Date(2019,5,3),72,new Double(126798.75),null,""));
        userList.add(new User("17","רחל","בן משה","F",new Date(1971,3,16),new Date(2006,1,18),new Double(27205.2421875),new Date(2006,1,18),72,new Double(0),null,""));
        userList.add(new User("18","נקול","פטרוק","F",new Date(1971,6,15),new Date(2010,8,28),new Double(33498.5625),new Date(2010,8,28),100,new Double(0),null,""));
        userList.add(new User("19","אנטולי","וסרמן","M",new Date(1972,2,12),new Date(2016,10,30),new Double(32799.3046875),new Date(2019,4,2),100,new Double(42266.25),null,""));
        userList.add(new User("20","נורית","שמיר","F",new Date(1973,4,2),new Date(2016,11,28),new Double(14618.6015625),null,0,new Double(101439),new Date(2021,12,1),"התפטרות"));
        userList.add(new User("21","משה","שלמה ","M",new Date(1972,6,7),new Date(2000,12,28),new Double(30701.53125),new Date(2000,12,28),100,new Double(0),null,""));
        userList.add(new User("22","טל","בואדנו","F",new Date(1972,9,24),new Date(2010,12,10),new Double(70419.375),new Date(2019,3,31),72,new Double(150000),null,""));
        userList.add(new User("23","בוריס","לויטין","M",new Date(1973,2,28),new Date(2016,1,30),new Double(36970.4940820312),new Date(2019,3,31),100,new Double(80000),new Date(2021,12,6),"התפטרות"));
        userList.add(new User("24","אור","בוזגלו","M",new Date(1980,7,21),new Date(2016,11,1),new Double(36855),new Date(2016,11,1),100,new Double(0),null,""));
        userList.add(new User("25","שי","צוריקר","M",new Date(1975,1,29),new Date(2010,10,29),new Double(14618.6015625),new Date(2010,10,29),100,new Double(0),null,""));
        userList.add(new User("26","אוסנת","רוטברג","F",new Date(1975,5,22),new Date(2016,12,17),new Double(32478.9280664062),new Date(2019,5,31),100,new Double(70000),null,""));
        userList.add(new User("27","אבי","שטרן","M",new Date(1977,4,29),new Date(2006,1,18),new Double(18114.890625),new Date(2006,1,18),100,new Double(0),null,""));
        userList.add(new User("28","מיכל","ברדה","F",new Date(1977,3,5),new Date(2016,1,30),new Double(34897.078125),null,0,new Double(211331.25),null,""));
        userList.add(new User("29","מוטי","סילברבג","M",new Date(1977,9,19),new Date(2012,12,28),new Double(30418.5649218749),new Date(2012,12,28),100,new Double(0),new Date(2021,4,3),"התפטרות"));
        userList.add(new User("30","שרון","קמחי","M",new Date(1981,4,16),new Date(2014,10,24),new Double(15317.859375),new Date(2014,10,24),100,new Double(0),null,""));
        userList.add(new User("31","נעה","מיכלשוילי","F",new Date(1985,7,21),new Date(2016,2,26),new Double(12818.711953125),new Date(2016,2,26),100,new Double(0),null,""));
        userList.add(new User("32","מוחמד","אבוארר","M",new Date(1985,7,22),new Date(2007,8,17),new Double(26505.984375),null,0,new Double(152158.5),null,""));
        userList.add(new User("33","ליאת","סעדיה","F",new Date(1986,5,1),new Date(2007,1,30),new Double(12066.310546875),null,0,new Double(67626),null,""));
        userList.add(new User("34","סטס","ברזובסקי","M",new Date(1979,11,23),new Date(2001,12,1),new Double(39092.625),new Date(2019,10,7),100,new Double(202878),null,""));
        userList.add(new User("35","שילו","ממון","M",new Date(1984,1,3),new Date(2015,4,6),new Double(15597.5625),new Date(2015,4,6),100,new Double(0),new Date(2021,10,28),"התפטרות"));
        userList.add(new User("36","דניאל","סרגון","M",new Date(1976,7,21),new Date(2009,6,1),new Double(12520.828125),new Date(2009,6,1),100,new Double(0),null,""));
        userList.add(new User("37","אשר","ורדה","M",new Date(1976,9,12),new Date(2006,5,1),new Double(25806.7265625),new Date(2019,1,1),100,new Double(126798.75),null,""));
        userList.add(new User("38","גבי","בן דוד","M",new Date(1976,9,16),new Date(2011,3,4),new Double(22310.4375),new Date(2011,3,4),72,new Double(0),null,""));
        userList.add(new User("39","נדב","זקס","M",new Date(1984,12,12),new Date(2012,7,27),new Double(16017.1171875),new Date(2012,7,27),100,new Double(0),null,""));
        userList.add(new User("40","שרלי","רם","F",new Date(1997,1,21),new Date(2018,9,28),new Double(12171.19921875),new Date(2018,9,28),100,new Double(0),new Date(2021,1,25),"פיטורין"));
        userList.add(new User("41","אירנה","ולקוב","F",new Date(1959,5,23),new Date(2001,4,1),new Double(19233.703125),new Date(2014,7,30),100,new Double(130000),null,""));
        userList.add(new User("42","אנה","בלושטיין","F",new Date(1997,7,7),new Date(2016,9,3),new Double(13220.0859375),new Date(2016,9,3),100,new Double(0),null,""));
        userList.add(new User("43","נטלי","קלימן","F",new Date(1982,4,4),new Date(2012,8,6),new Double(19513.40625),new Date(2012,8,6),100,new Double(0),null,""));
        userList.add(new User("44","חיים","שפיגל","M",new Date(1976,6,25),new Date(2010,8,28),new Double(20142.73828125),new Date(2010,8,28),72,new Double(0),null,""));
        userList.add(new User("45","לימור","גרוטו","F",new Date(1999,7,16),new Date(2019,9,5),new Double(12171.19921875),new Date(2019,9,5),72,new Double(0),null,""));
        userList.add(new User("46","דיאנה","וקסלר","F",new Date(1959,6,24),new Date(1996,12,1),new Double(22310.4375),null,0,new Double(295863.75),null,""));
        userList.add(new User("47","ולנטינה","ספוגניקוב","F",new Date(1958,11,4),new Date(2013,8,23),new Double(15317.859375),new Date(2013,8,23),72,new Double(0),null,""));
        userList.add(new User("48","רחל","מור","F",new Date(1974,9,29),new Date(1998,10,1),new Double(22590.140625),new Date(2015,2,1),72,new Double(200000),null,""));
        userList.add(new User("49","רומן","ברון","M",new Date(1952,10,18),new Date(2000,4,10),new Double(11553.0553125),new Date(2014,3,30),100,new Double(100000),new Date(2021,7,31),"פרישה לגמלאות"));
        userList.add(new User("50","אילן","ברורמן","M",new Date(1969,9,22),new Date(2016,10,29),new Double(34897.078125),new Date(2016,10,29),100,new Double(0),null,""));
        userList.add(new User("51","רות","שקולניק","F",new Date(1974,8,26),new Date(2008,6,14),new Double(15808.738359375),new Date(2008,6,14),72,new Double(0),new Date(2021,2,1),"פיטורין"));
        userList.add(new User("52","שלמה","ברודי","M",new Date(1978,11,12),new Date(2016,10,30),new Double(36855),new Date(2016,10,30),100,new Double(0),new Date(2021,12,31),"פיטורין"));
        userList.add(new User("53","איגל","פז","M",new Date(1986,6,2),new Date(2012,9,28),new Double(31400.7890625),new Date(2012,9,28),100,new Double(0),new Date(2021,6,1),"פיטורין"));
        userList.add(new User("54","יואב","מורדכי","M",new Date(1984,4,18),new Date(2017,5,3),new Double(23708.953125),new Date(2017,5,3),100,new Double(0),new Date(2021,12,31),"פיטורין"));
        userList.add(new User("55","דוד","זינו","M",new Date(1980,8,4),new Date(2016,5,3),new Double(48043.125),new Date(2016,5,3),100,new Double(0),new Date(2021,12,31),"פיטורין"));
        userList.add(new User("56","רונן","פלדמן","M",new Date(1981,10,3),new Date(2007,2,15),new Double(35316.6328125),null,0,new Double(211331.25),new Date(2021,12,31),"פיטורין"));
        userList.add(new User("57","עודד","גליקסמן","M",new Date(1960,3,5),new Date(2016,12,8),new Double(41260.32421875),new Date(2016,12,8),100,new Double(0),new Date(2021,12,31),"פיטורין"));
        userList.add(new User("58","אביטל","שליט","F",new Date(1998,2,4),new Date(2016,4,1),new Double(30701.53125),new Date(2016,4,1),100,new Double(0),new Date(2021,5,2),"פיטורין"));
        userList.add(new User("59","אגם","זאן","F",new Date(1985,4,14),new Date(2015,11,16),new Double(20911.921875),new Date(2015,11,16),100,new Double(0),new Date(2021,12,31),"פיטורין"));
        userList.add(new User("60","אלנה","קרבצוב","F",new Date(1958,3,28),new Date(1999,7,1),new Double(17849.17265625),new Date(2017,12,1),72,new Double(250000),new Date(2021,1,1),"פיטורין"));
        userList.add(new User("61","דרור","כהן","M",new Date(1976,5,16),new Date(2008,8,1),new Double(20191.686328125),null,0,new Double(118345.5),new Date(2021,1,1),"פיטורין"));
        userList.add(new User("62","מור","שויט","M",new Date(1985,6,16),new Date(2017,3,30),new Double(25107.46875),new Date(2017,3,30),100,new Double(0),new Date(2021,12,31),"פיטורין"));
        userList.add(new User("63","ולד","ליברמן","M",new Date(1983,5,9),new Date(2017,3,30),new Double(23708.953125),new Date(2017,3,30),100,new Double(0),new Date(2021,12,31),"פיטורין"));
        userList.add(new User("64","חנה","שניידר","F",new Date(1954,8,29),new Date(2001,12,1),new Double(30142.125),null,0,new Double(211331.25),new Date(2021,12,31),"פיטורין"));
        userList.add(new User("65","יוסי","קפלן","M",new Date(1971,2,17),new Date(2005,3,4),new Double(37694.109375),null,0,new Double(295863.75),new Date(2021,10,4),"פיטורין"));
        userList.add(new User("66","פאינה","בלוגולבסקי","F",new Date(1976,3,10),new Date(2016,3,3),new Double(30701.53125),new Date(2016,3,3),100,new Double(0),new Date(2021,12,31),"פיטורין"));
        userList.add(new User("67","סמדר","שילו","F",new Date(1967,5,22),new Date(2007,7,1),new Double(13220.0859375),null,0,new Double(66780.675),new Date(2021,12,31),"פיטורין"));
        userList.add(new User("68","נחום","ברוך","M",new Date(1969,6,9),new Date(2014,9,2),new Double(42449.0625),new Date(2014,9,2),72,new Double(0),new Date(2021,12,31),"פיטורין"));
        userList.add(new User("69","דבורה","לבינסקי","F",new Date(1969,10,9),new Date(2003,9,1),new Double(26394.103125),null,0,new Double(228237.75),new Date(2021,12,31),"פיטורין"));
        userList.add(new User("70","איתן","מנדל","M",new Date(1970,5,28),new Date(2016,9,1),new Double(24548.0625),new Date(2016,9,1),100,new Double(0),new Date(2021,7,24),"פיטורין"));
        userList.add(new User("71","משה","דרור","M",new Date(1970,7,22),new Date(2017,4,1),new Double(34897.078125),new Date(2017,4,1),72,new Double(0),null,""));
        userList.add(new User("72","טליה","לבנון","F",new Date(1973,3,11),new Date(2004,4,1),new Double(48043.125),null,0,new Double(422662.5),null,""));
        userList.add(new User("73","מוריה","גקסון","F",new Date(1973,6,18),new Date(2000,12,17),new Double(21191.625),new Date(2014,6,30),100,new Double(101439),null,""));
        userList.add(new User("74","הילה","אלטרמן","F",new Date(1973,5,15),new Date(2005,3,8),new Double(24704.69625),null,0,new Double(211331.25),null,""));
        userList.add(new User("75","אורית","רגב","F",new Date(1974,1,18),new Date(2006,4,15),new Double(23177.5171875),null,0,new Double(169065),null,""));
        userList.add(new User("76","מאיר","שלונסקי","M",new Date(1975,2,28),new Date(2007,1,8),new Double(27456.975),null,0,new Double(185971.5),null,""));
        userList.add(new User("77","מיטל","שריקי","F",new Date(1974,10,27),new Date(2014,11,28),new Double(22422.31875),new Date(2014,11,28),100,new Double(0),null,""));
        userList.add(new User("78","דינה","כוגן","F",new Date(1971,6,14),new Date(2016,9,11),new Double(27345.09375),new Date(2016,9,11),100,new Double(0),new Date(2021,12,3),"התפטרות"));
        userList.add(new User("79","טהילה","וקנין","F",new Date(1971,6,4),new Date(2017,4,15),new Double(18394.59375),new Date(2017,4,15),72,new Double(0),null,""));
        userList.add(new User("80","רות","בן משה","F",new Date(1972,2,2),new Date(2016,11,28),new Double(31820.34375),new Date(2016,11,28),100,new Double(0),null,""));
        userList.add(new User("81","אמיליה","קצנלסון","F",new Date(1975,5,20),new Date(2004,7,19),new Double(21903.469453125),new Date(2019,1,2),72,new Double(225000),null,""));
        userList.add(new User("82","חגית","נחמני","F",new Date(1975,3,15),new Date(2015,8,18),new Double(30701.53125),new Date(2015,8,18),100,new Double(0),null,""));
        userList.add(new User("83","ליאור","שבתאי","F",new Date(1979,1,20),new Date(2010,10,3),new Double(20562.29296875),new Date(2019,3,31),100,new Double(150000),new Date(2021,8,1),"התפטרות"));
        userList.add(new User("84","בוריס","ורניק","M",new Date(1976,12,3),new Date(2015,10,29),new Double(29582.71875),new Date(2015,10,29),100,new Double(0),null,""));
        userList.add(new User("85","עינב","כץ ","F",new Date(1977,11,26),new Date(2008,3,15),new Double(13919.34375),new Date(2012,12,1),72,new Double(30000),null,""));
        userList.add(new User("86","עדי","חסן","F",new Date(1978,5,28),new Date(2010,1,9),new Double(23899.15125),new Date(2010,1,9),100,new Double(0),null,""));
        userList.add(new User("87","דוד","רוזנברג","M",new Date(1978,10,3),new Date(2010,12,10),new Double(32100.046875),new Date(2010,12,10),50,new Double(0),null,""));
        userList.add(new User("88","שלומית","גירין","F",new Date(1979,8,15),new Date(2015,7,15),new Double(27904.5),new Date(2015,7,15),50,new Double(0),null,""));
        userList.add(new User("89","שרון","חביה תקומי","M",new Date(1979,5,28),new Date(2005,2,17),new Double(41889.65625),null,0,new Double(295863.75),new Date(2021,7,6),"התפטרות"));
        userList.add(new User("90","דניאל","עמית","F",new Date(1985,3,22),new Date(2012,2,16),new Double(28463.90625),new Date(2017,5,30),100,new Double(70000),null,""));
        userList.add(new User("91","נעמה","חן","F",new Date(1985,7,21),new Date(2010,8,16),new Double(15485.68125),null,0,new Double(59172.75),null,""));
        userList.add(new User("92","נדב","בנט","M",new Date(1976,1,12),new Date(2016,7,21),new Double(14856.34921875),new Date(2016,7,21),100,new Double(0),null,""));
        userList.add(new User("93","חנה","ענבר","F",new Date(1976,5,3),new Date(2016,1,30),new Double(12974.3661548109),new Date(2016,1,30),100,new Double(0),null,""));
        userList.add(new User("94","ליאת","בר","F",new Date(1976,10,8),new Date(2006,8,1),new Double(16716.375),null,0,new Double(169065),null,""));
        userList.add(new User("95","רחל","חדד","F",new Date(1984,1,13),new Date(2016,11,2),new Double(18954),new Date(2016,11,2),100,new Double(0),null,""));
        userList.add(new User("96","מרינה","קדוש","M",new Date(1984,7,10),new Date(2016,8,28),new Double(25107.46875),new Date(2016,8,28),50,new Double(0),null,""));
        userList.add(new User("97","איתן","ספיר","M",new Date(1984,2,26),new Date(2016,7,22),new Double(19849.05),new Date(2016,7,22),50,new Double(0),null,""));
        userList.add(new User("98","דבורה","וקנין","F",new Date(1982,3,10),new Date(2007,1,22),new Double(25868.26125),null,0,new Double(59172.75),null,""));
        userList.add(new User("99","לוסי","שרחה ","F",new Date(1963,11,3),new Date(2005,9,16),new Double(30785.4421875),null,0,new Double(253597.5),null,""));
        userList.add(new User("100","שגיא","ביסטרוב","M",new Date(1963,9,10),new Date(2017,1,12),new Double(48043.125),new Date(2017,1,12),100,new Double(0),null,""));
        userList.add(new User("101","חוה","אינוטייב","F",new Date(1983,1,5),new Date(2007,4,7),new Double(34197.8203125),new Date(2014,10,30),100,new Double(200000),null,""));
        userList.add(new User("102","בני","רופא","M",new Date(1987,8,22),new Date(2017,3,3),new Double(23988.65625),new Date(2017,3,3),50,new Double(0),null,""));
        userList.add(new User("103","שמעון","לוי איסקוב","M",new Date(1988,11,28),new Date(2017,3,17),new Double(29792.49609375),new Date(2017,3,17),50,new Double(0),null,""));
        userList.add(new User("104","דניאל","קרייטנברגר","M",new Date(1989,10,19),new Date(2017,5,30),new Double(16156.96875),new Date(2017,5,30),100,new Double(0),null,""));
        userList.add(new User("105","חיים","ישראל","M",new Date(1989,6,25),new Date(2016,10,6),new Double(18779.185546875),new Date(2016,10,6),100,new Double(0),null,""));
        userList.add(new User("106","שירה","הרוש","F",new Date(1989,7,19),new Date(2016,11,1),new Double(25107.46875),new Date(2016,11,1),50,new Double(0),null,""));
        userList.add(new User("107","אלקס","פלדמן","M",new Date(1994,11,13),new Date(2016,7,15),new Double(27904.5),new Date(2016,7,15),50,new Double(0),null,""));
        userList.add(new User("108","סבטלנה","טישלר ","F",new Date(1969,3,12),new Date(2010,10,29),new Double(30701.53125),new Date(2010,10,29),50,new Double(0),null,""));
        userList.add(new User("109","נחום","מובשוביץ","M",new Date(1978,5,21),new Date(2017,3,24),new Double(27694.72265625),new Date(2017,3,24),50,new Double(0),null,""));
        userList.add(new User("110","גבי","פראח ","F",new Date(1970,11,28),new Date(2016,12,17),new Double(34057.96875),new Date(2016,12,17),50,new Double(0),null,""));
        userList.add(new User("111","רינה","רם","F",new Date(1955,10,12),new Date(2003,7,1),new Double(31260.9375),null,0,new Double(338130),null,""));
        userList.add(new User("112","אמנון","שחר","M",new Date(1974,9,12),new Date(2005,12,11),new Double(27654.165703125),null,0,new Double(228237.75),null,""));
        userList.add(new User("113","אלון","מלכ","M",new Date(1989,9,3),new Date(2016,9,13),new Double(27345.09375),new Date(2016,9,13),50,new Double(0),null,""));
        userList.add(new User("114","מוטי","גורן","M",new Date(1980,8,29),new Date(2007,8,17),new Double(18954),null,0,new Double(126798.75),new Date(2021,4,3),"התפטרות"));
        userList.add(new User("115","אלונה","כהן","F",new Date(1961,11,12),new Date(2004,1,11),new Double(20212.6640625),null,0,new Double(109892.25),null,""));
        userList.add(new User("116","בר","עזיז","F",new Date(1974,6,26),new Date(2016,1,30),new Double(32379.75),new Date(2016,1,30),100,new Double(0),null,""));
        userList.add(new User("117","רות","טוביאנה","F",new Date(1954,11,18),new Date(2004,10,6),new Double(26925.5390625),null,0,new Double(139821.970074895),null,""));
        userList.add(new User("118","אנה ","לוי ","F",new Date(1954,5,10),new Date(2007,6,6),new Double(19289.64375),null,0,new Double(132657.890485514),null,""));
        userList.add(new User("119","טל","לב","M",new Date(1963,9,28),new Date(2005,8,12),new Double(24408.2109375),null,0,new Double(46990.9454038256),null,""));
        userList.add(new User("120","חנן","אלמקיאס","M",new Date(1976,1,19),new Date(2009,4,11),new Double(25107.46875),new Date(2009,4,11),100,new Double(0),null,""));
        userList.add(new User("121","רוביק","דקל","M",new Date(1960,4,26),new Date(1990,11,25),new Double(32100.046875),new Date(1990,11,25),50,new Double(0),null,""));
        userList.add(new User("122","שלמה","ברק","M",new Date(1954,8,4),new Date(2003,7,1),new Double(30701.53125),null,0,new Double(277053.129681699),null,""));
        userList.add(new User("123","מורד","עבד אל רחמן","M",new Date(1994,1,19),new Date(2016,8,3),new Double(11681.71875),new Date(2016,8,3),100,new Double(0),null,""));
        userList.add(new User("124","סתיו ","פומגרין","M",new Date(1985,8,22),new Date(2012,12,28),new Double(8884.6875),new Date(2012,12,28),100,new Double(0),null,""));
        userList.add(new User("125","אליס","סרוסי ","F",new Date(1979,12,25),new Date(2013,5,1),new Double(8884.6875),new Date(2013,5,1),100,new Double(0),null,""));
        userList.add(new User("126","עדן","מור","F",new Date(1966,6,14),new Date(2015,2,28),new Double(10143.3515625),null,0,new Double(14053.7407352843),null,""));
        userList.add(new User("127","איתמר","שמעוני","M",new Date(1993,4,27),new Date(2014,11,10),new Double(10562.90625),null,0,new Double(30000),new Date(2021,5,7),"התפטרות"));
        userList.add(new User("128","גל","גלעדי","F",new Date(1981,1,19),new Date(2015,4,4),new Double(10143.3515625),null,0,new Double(30000),new Date(2021,9,4),"התפטרות"));
        userList.add(new User("129","שי","שמעון","M",new Date(1966,10,28),new Date(2014,9,18),new Double(11122.3125),null,0,new Double(16453.9266569644),null,""));
        userList.add(new User("130","רביטל","פרנקו","F",new Date(1975,11,9),new Date(2014,5,1),new Double(11471.94140625),null,0,new Double(16842.0716459046),null,""));
        userList.add(new User("131","דפנה","מזרחי","F",new Date(1981,5,3),new Date(2014,5,1),new Double(20911.921875),new Date(2014,5,1),50,new Double(0),null,""));
        userList.add(new User("132","נדין","גרייב ","F",new Date(1987,7,13),new Date(2014,5,1),new Double(10842.609375),null,0,new Double(15883.9641205829),null,""));
        userList.add(new User("133","מוחמד","טאהא","M",new Date(1974,6,16),new Date(2014,10,24),new Double(22310.4375),new Date(2014,10,24),100,new Double(0),null,""));
        userList.add(new User("134","עינב","בן משה","F",new Date(1976,12,8),new Date(2014,5,1),new Double(10842.609375),null,0,new Double(21343.5947810149),null,""));
        userList.add(new User("135","יעל","בזאליי","F",new Date(1990,1,9),new Date(2014,5,1),new Double(16716.375),null,0,new Double(30460.5427811264),null,""));
        userList.add(new User("136","סמדר","פרץ","F",new Date(1980,12,11),new Date(2014,5,1),new Double(11262.1640625),null,0,new Double(15721.3451938609),null,""));
        userList.add(new User("137","תומר","אקוקה","M",new Date(1967,10,7),new Date(2014,5,1),new Double(15982.154296875),null,0,new Double(40819.6280047892),null,""));
        userList.add(new User("138","שי","גוסלקר","M",new Date(1972,1,21),new Date(2016,2,26),new Double(32799.3046875),new Date(2016,2,26),100,new Double(0),null,""));
        userList.add(new User("139","נפתלי","נאשף","M",new Date(1977,10,27),new Date(2014,5,1),new Double(16716.375),new Date(2014,5,1),50,new Double(0),null,""));
        userList.add(new User("140","שרה","פיקלר","F",new Date(1978,12,3),new Date(2017,2,20),new Double(11402.015625),null,0,new Double(8884.31900563211),null,""));
        userList.add(new User("141","לינוי","אדרי","F",new Date(1997,5,6),new Date(2016,1,13),new Double(10143.3515625),null,0,new Double(20000),new Date(2021,3,6),"התפטרות"));
        userList.add(new User("142","בני","דוארי","M",new Date(1971,7,9),new Date(2016,8,19),new Double(15317.859375),null,0,new Double(9998.80252169745),null,""));
        userList.add(new User("143","חדוה","אמדו","F",new Date(1982,5,3),new Date(2016,9,13),new Double(10562.90625),null,0,new Double(3680.48946174201),null,""));
        userList.add(new User("144","אבי","יעקב ","M",new Date(1984,6,13),new Date(2016,7,1),new Double(34848.130078125),new Date(2016,7,1),100,new Double(0),null,""));
        userList.add(new User("145","מוריה","מושידה","F",new Date(1984,10,10),new Date(2016,7,27),new Double(10423.0546875),null,0,new Double(20000),new Date(2021,7,5),"התפטרות"));
        userList.add(new User("146","נעה","חנינה","F",new Date(1993,4,30),new Date(2016,11,21),new Double(15667.48828125),null,0,new Double(9274.70157749318),null,""));
        userList.add(new User("147","רון","לקסמן","M",new Date(1985,6,13),new Date(2016,10,1),new Double(17415.6328125),null,0,new Double(10547.4721871527),null,""));
        userList.add(new User("148","מקסים","גרובר","M",new Date(1981,2,10),new Date(2016,10,8),new Double(13919.34375),null,0,new Double(8754.41178936813),null,""));
        userList.add(new User("149","פני","מוזס","F",new Date(1986,9,19),new Date(2016,11,13),new Double(10283.203125),null,0,new Double(5422.84989348803),null,""));
        userList.add(new User("150","מרינה","בר לב","F",new Date(1995,10,24),new Date(2016,11,14),new Double(10562.90625),null,0,new Double(5599.47561151864),null,""));
        //106-150
        /*
        userList.add(new User("1","חיים","גלוקמן","M", new Date(1985,6,9) ,new Date(2013,12,10),new Double(7000),new Date(2013,12,10),100,new Double(0),new Date(1,1,1),""));
        userList.add(new User("2","שרה","אברג'ל","F", new Date(1985,5,23) ,new Date(2014,7,10),new Double(21000),new Date(2014,7,10),72,new Double(0),new Date(1,1,1),""));
        userList.add(new User("4","אירית","אשכנזי","F", new Date(1967,5,6) ,new Date(2002,7,7),new Double(10000),null,0,new Double(128799),new Date(1,1,1),""));
        userList.add(new User("7","חיה","לוי","F", new Date(1968,4,1) ,new Date(2019,1,3),new Double(23668),new Date(2019,1,3),100,new Double(0),new Date(1,1,1),""));
        userList.add(new User("9","אביגל","מצטנר","F", new Date(1972,11,29) ,new Date(2019,12,1),new Double(10700),new Date(2019,12,1),100,new Double(0),new Date(1,1,1),""));
        userList.add(new User("10","אבי","גולדמן","M", new Date(1973,5,11) ,new Date(2017,2,21),new Double(8500),new Date(2017,2,21),72,new Double(0),new Date(1,1,1),""));
        userList.add(new User("11","משה","אבולפיה","M", new Date(1973,5,1) ,new Date(2002,9,1),new Double(23000),null,0,new Double(309279),new Date(1,1,1),""));
        userList.add(new User("12","אלקס","פלטינקוב","M", new Date(1973,6,17) ,new Date(2019,1,20),new Double(6500),new Date(2019,1,20),100,new Double(0),new Date(1,1,1),""));
        userList.add(new User("13","מיכאל","גולד","M", new Date(1973,8,27) ,new Date(2000,11,7),new Double(24500),new Date(2016,1,1),100,new Double(224888),new Date(1,1,1),""));
        userList.add(new User("15","עדי","וקסמן","M", new Date(1970,6,15) ,new Date(2011,8,14),new Double(37000),new Date(2011,8,14),72,new Double(0),new Date(1,1,1),""));
        userList.add(new User("16","אסנת","גולדברג","F", new Date(1971,9,4) ,new Date(2004,1,4),new Double(9000),new Date(2017,4,1),72,new Double(115887),new Date(1,1,1),""));
        userList.add(new User("17","רחל","בן משה","F", new Date(1970,11,16) ,new Date(2017,6,20),new Double(17500),new Date(2017,6,20),72,new Double(0),new Date(1,1,1),""));
        userList.add(new User("18","נקול","פטרוק","F", new Date(1971,2,15) ,new Date(2018,12,26),new Double(22000),new Date(2018,12,26),100,new Double(0),new Date(1,1,1),""));
        userList.add(new User("19","אנטולי","וסרמן","M", new Date(1971,10,12) ,new Date(2016,1,5),new Double(21500),new Date(2016,1,5),100,new Double(0),new Date(1,1,1),""));
        */
    }

}