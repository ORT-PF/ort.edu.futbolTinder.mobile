package ort.edu.ar.futboltinder.activities.home.fragments

import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import ort.edu.ar.futboltinder.R
import ort.edu.ar.futboltinder.domain.Match.Forms.MatchCreatorForm
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.LocalTime
import android.app.TimePickerDialog.OnTimeSetListener


class MatchCreatorFragment : Fragment() {
    lateinit var vista: View
    lateinit var matchCreateButton: Button
    lateinit var nameText: EditText
    lateinit var quotaNumber: EditText
    lateinit var dateField: EditText
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    lateinit var startTime: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val defaultDateString = LocalDate.now().format(formatter)

        vista = inflater.inflate(R.layout.fragment_match_creator, container, false)

        nameText = vista.findViewById(R.id.nameMatchCreatorForm)
        quotaNumber = vista.findViewById(R.id.quotaMatchCreatorForm)
        matchCreateButton = vista.findViewById(R.id.buttonMatchCreatorForm)
        dateField = vista.findViewById(R.id.date_field)
        dateField.setText(defaultDateString)
        dateField.setOnClickListener {
            showDatePicker()
        }

        setUpStartTime()

        return vista
    }

    private fun showDatePicker() {
        val date = getCurrentDate()
        DatePickerFragment.newInstance(
            date.year,
            date.monthValue,
            date.dayOfMonth
        ) { _, year, month, day ->
            dateField.setText(formatDate(year, month, day))
        }.show(getParentFragmentManager(), "date-picker")
    }

    private fun getCurrentDate(): LocalDate {
        val date = dateField.text.toString()
        return LocalDate.parse(date, formatter)
    }

    private fun formatDate(year: Int, month: Int, day: Int): String {
        val sanitizeMonth = month + 1
        return LocalDate.of(year, sanitizeMonth, day).format(formatter)
    }


    override fun onStart() {
        super.onStart()

        matchCreateButton.setOnClickListener {
            var isValidContext = validateContext()

            if (isValidContext) {
                var matchCreatorForm = MatchCreatorForm(
                    nameText.text.toString(),
                    quotaNumber.text.toString().toInt(),
                    null,
                    dateField.text.toString() + startTime.text.toString(),
                    null,
                    null
                )

                val action =
                    MatchCreatorFragmentDirections.actionMatchCreatorFragmentToMapsFragment(matchCreatorForm)
                vista.findNavController().navigate(action)
            }
        }
    }

        private fun setUpStartTime() {
            startTime = vista.findViewById(R.id.from_time)
            startTime.text = LocalTime.now().toTimeText()
            startTime.setOnClickListener {
                showStartTimePicker()
            }
        }

        private fun showStartTimePicker() {
            val time = startTime.getTime()
            showDialog(time.hour, time.minute) { _, hour, minute ->
            val currentTime = LocalTime.of(hour, minute)
                startTime.setTime(currentTime)
            }
        }

        private fun showDialog(initialHour: Int, initialMinute: Int, observer: TimePickerDialog.OnTimeSetListener) {
            TimePickerFragment.newInstance(initialHour, initialMinute, observer)
                .show(getParentFragmentManager(), "time-picker")
        }

        private fun validateContext(): Boolean {
            if (nameText.text.isNullOrEmpty()) {
                Toast.makeText(activity, "El nombre de la cancha es requerido", Toast.LENGTH_LONG).show()
                return false
            }
            if (quotaNumber.text.isNullOrEmpty()) {
                Toast.makeText(activity, "El cupo de jugadores es requerido", Toast.LENGTH_LONG).show()
                return false
            }
            return true
        }
    }
