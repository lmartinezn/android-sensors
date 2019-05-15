package cat.tecnocampus.mobileapps.practica1.lightsensortest;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private TextView textValues;
    private SensorManager mSensorManager;
    private Sensor lightSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textValues = findViewById(R.id.values_text);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if(mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null){
            lightSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        }else{
            // Failure!!
            Log.d("SwA", "No Light sensor available!");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(lightSensor != null){
            mSensorManager.registerListener(this,
                    lightSensor,
                    SensorManager.SENSOR_DELAY_NORMAL); //Delay, cada quant rebrem valors
        }
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(this); //Ens de-registrem abans de pausar la nostra app
        super.onPause();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float lux = sensorEvent.values[0];
        String oldValue = textValues.getText().toString();
        textValues.setText(lux + "\n" + oldValue );
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
