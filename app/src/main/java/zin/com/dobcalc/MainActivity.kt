package zin.com.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate :TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById<Button>(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)

        btnDatePicker.setOnClickListener(){
            clickDatePicker()
        }
    }

    fun clickDatePicker(){
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this,
        DatePickerDialog.OnDateSetListener{view, selectedYear, selectedMonth, selectedDayOfMonth ->
            Toast.makeText(this,
                "year ${selectedYear}, month ${selectedMonth+1} dayOfMonth ${selectedDayOfMonth}", Toast.LENGTH_LONG).show()

            val selectedDate = "${selectedYear}/${selectedMonth+1}/${selectedDayOfMonth}"

            tvSelectedDate?.setText(selectedDate)

            val sdf = SimpleDateFormat("yyyy/MM//dd", Locale.ENGLISH)

            val theDate = sdf.parse(selectedDate)

        },
        year, month, day
            ).show()

    }


}