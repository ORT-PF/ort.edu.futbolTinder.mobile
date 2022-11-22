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
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*


class MatchCreatorFragment : Fragment() {
    lateinit var vista: View
    lateinit var matchCreateButton: Button
    lateinit var nameText: EditText
    lateinit var quotaNumber: EditText
    lateinit var dateField: EditText
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val formatterApi = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a", Locale.ENGLISH)
    lateinit var startTime: TextView
    val maxPlayers = 9

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.title =
            "Crear Partido"
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
                    formatDateTimeToApiFormat(),
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
            val currentTime = LocalTime.of(hour, minute, 0)
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

            val str = quotaNumber.text
            val num = Integer.parseInt(str.toString())

            if (num > maxPlayers) {
                Toast.makeText(
                    activity,
                    "La cantidad de jugadores debe estar entre 1 y 9",
                    Toast.LENGTH_LONG
                ).show()
                return false
            }
            return true
        }

        private fun formatDateTimeToApiFormat() : String{
            var pickerDate = dateField.text.toString()+" "+startTime.text.toString()
            var matchDate = LocalDateTime.parse(pickerDate, formatterApi)
            matchDate.atZone(ZoneId.of("UTC-3"))

            return matchDate.toString()
        }
    }
