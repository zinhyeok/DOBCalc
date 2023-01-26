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
    private var tvAgeInMinute :TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById<Button>(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinute = findViewById(R.id.tvAgeInMinutes)

        btnDatePicker.setOnClickListener(){
            clickDatePicker()
        }
    }

    private fun clickDatePicker(){
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{view, selectedYear, selectedMonth, selectedDayOfMonth ->

                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

                tvSelectedDate?.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)
                theDate?.let {
                    val selectedDateInMinutes = theDate.time / 60000
                    //1970 부터 select까지 시간을 milisec 단위로 구함 / 60000 -> min단위로 변경
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    //1970 부터 현재까지 시간을 milisec 단위로 구함
                    currentDate?.let {
                        val currentDateInMinutes = currentDate.time /60000
                        val diffInMin = currentDateInMinutes - selectedDateInMinutes
//                Toast.makeText(this, "$theDate", Toast.LENGTH_LONG).show()
                        tvAgeInMinute?.text = diffInMin.toString()
                    }
                }

            },
            year, month, day
        )

        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }


}