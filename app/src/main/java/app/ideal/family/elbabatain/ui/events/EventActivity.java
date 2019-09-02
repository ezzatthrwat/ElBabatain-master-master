package app.ideal.family.elbabatain.ui.events;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;

import com.shrikanthravi.collapsiblecalendarview.data.Day;
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import app.ideal.family.elbabatain.R;
import app.ideal.family.elbabatain.network.events.Events_Model;
import app.ideal.family.elbabatain.viewmodels.ViewModelProviderFactory;
import dagger.android.support.DaggerAppCompatActivity;

import static app.ideal.family.elbabatain.util.BubbleAnimation.DoAnimation;

public class EventActivity extends DaggerAppCompatActivity {

    private static final String TAG = "EventActivity";

    @Inject
    Events_Adapter events_adapter ;

    @Inject
    LinearLayoutManager linearLayoutManager ;

    @Inject
    ViewModelProviderFactory providerFactory;
    Event_ViewModel event_viewModel;

    private RecyclerView EventsRecyclerView ;
    private Button button ;
    private CollapsibleCalendar collapsibleCalendar ;

    private String SelectedDate ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        event_viewModel = ViewModelProviders.of(this , providerFactory).get(Event_ViewModel.class);

        initWidget();
        getAllEventsFromDatabase();
    }

    private void initWidget(){

        button = findViewById(R.id.AllEvents_BTN);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllEventsFromDatabase();

            }
        });

        EventsRecyclerView = findViewById(R.id.EventsRecyclerView);
        EventsRecyclerView.setHasFixedSize(true);
        EventsRecyclerView.setLayoutManager(linearLayoutManager);

        collapsibleCalendar = findViewById(R.id.calendarView);

        getDateFromCalendarAndGetEventDataByDate();
    }
    private void getDateFromCalendarAndGetEventDataByDate(){
        collapsibleCalendar.setCalendarListener(new CollapsibleCalendar.CalendarListener() {
            @Override
            public void onDaySelect() {
                Day day = collapsibleCalendar.getSelectedDay();

                SelectedDate = day.getYear() + "-" + (day.getMonth() + 1) + "-" + day.getDay() ;

                @SuppressLint("SimpleDateFormat")
                SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");

                try {
                    Date d=dateFormat.parse(SelectedDate);
//                    getAllEventsByDate(dateFormat.format(d));

                    getAllEventsFromDatabaseByDate(dateFormat.format(d));

                    Log.e(getClass().getName(), "Selected Day: " + dateFormat.format(d) );

                }
                catch(Exception ignored) {
                }
            }

            @Override
            public void onItemClick(View view) {

            }

            @Override
            public void onDataUpdate() {

            }

            @Override
            public void onMonthChange() {

            }

            @Override
            public void onWeekChange(int i) {

            }
        });
    }
    private void getAllEventsFromDatabase(){

        EventsRecyclerView.setAnimation(AnimationUtils.loadAnimation(EventActivity.this , R.anim.fade_transition_animation_from_left));

        event_viewModel.ALLEVENTS.observe(this, new Observer<List<Events_Model>>() {
            @Override
            public void onChanged(List<Events_Model> eventsModelList) {

                if (eventsModelList != null) {
                    events_adapter.setEvents_Adapter(EventActivity.this, eventsModelList);
                    EventsRecyclerView.setAdapter(events_adapter);
                    if (button.getVisibility() == View.VISIBLE) {
                        button.setVisibility(View.GONE);
                    }
                }
            }
        });
    }
    private void getAllEventsFromDatabaseByDate(String Date){

        event_viewModel.getAllEventsFromDatabaseByDate(Date).observe(this, new Observer<List<Events_Model>>() {
            @Override
            public void onChanged(List<Events_Model> eventsModelList) {

                if(eventsModelList != null){
                    events_adapter.setEvents_Adapter(EventActivity.this , eventsModelList);
                    EventsRecyclerView.setAdapter(events_adapter);
                    EventsRecyclerView.setAnimation(AnimationUtils.loadAnimation(EventActivity.this , R.anim.fade_transition_animation_from_left));
                    button.setVisibility(View.VISIBLE);
                    DoAnimation(button ,EventActivity.this);
                }

            }
        });
    }
}
