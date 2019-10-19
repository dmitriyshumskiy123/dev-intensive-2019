package ru.skillbranch.devintensive

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.Models.Bender

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var benderImage: ImageView
    lateinit var textTxt: TextView
    lateinit var messageEt: EditText
    lateinit var sendBtn: ImageView

    lateinit var benderObj: Bender

    //вызывается при первом создании или перезапуске Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //benderImage=findViewById(R.id.iv_bender)
        benderImage = iv_bender
        textTxt = tv_text
        messageEt = et_message
        sendBtn = iv_send

        val status = savedInstanceState?.getString("STATUS") ?: Bender.Status.NORMAL.name
        val question = savedInstanceState?.getString("QUESTION") ?: Bender.Question.NAME.name
        benderObj = Bender(Bender.Status.valueOf(status), Bender.Question.valueOf(question))

        Log.d("M_MainActivity", "onCreate $status $question")

        val (r,g,b) = benderObj.status.color
        //добавление эффекта наложения
        benderImage.setColorFilter(Color.rgb(r,g,b), PorterDuff.Mode.MULTIPLY)

        textTxt.text = benderObj.askQuestion()
        sendBtn.setOnClickListener(this)

    }

    //если Activity возвращается в приоритетный режим после onStop()
    override fun onRestart() {
        super.onRestart()
        setContentView(R.layout.activity_main)
        Log.d("M_MainActivity", "onRestart")
    }

    //при вызове onStart() окно еще не видно пользователю, но вскоре будет видно
    override fun onStart() {
        super.onStart()
        setContentView(R.layout.activity_main)
        Log.d("M_MainActivity", "onStart")
    }

    //вызывается, когда Activity начинает взаимодействовать с пользователем
    override fun onResume() {
        super.onResume()
        setContentView(R.layout.activity_main)
        Log.d("M_MainActivity", "onResume")
    }

    //вызывается после сворачивания текущей Activity или перехода к новой
    override fun onPause() {
        super.onPause()
        setContentView(R.layout.activity_main)
        Log.d("M_MainActivity", "onPause")
    }

    //вызывается, когда Activity становится невидимым для пользователя
    override fun onStop() {
        super.onStop()
        setContentView(R.layout.activity_main)
        Log.d("M_MainActivity", "onStop")
    }

    //вызывается по окончании работы Activity или при вызове метода finish()
    override fun onDestroy() {
        super.onDestroy()
        setContentView(R.layout.activity_main)
        Log.d("M_MainActivity", "onDestroy")
    }

    /*Этот метод сохраняет состояние представления в Bundle
    Для API Level < 28 этот метод будет выполняться до onStop(), и нет никаких гарантий относительно того,
    произойдет ли это до или после onPause().
    Для API Level >= 28 будет вызван после onStop()
    Не будет вызван, если Activity будет явно закрыта пользователем при нажатии на системную клавишу back.
    Bundle - это объект самого андроида, который позволяет сохранить состояние вашего интерфейса в тех случаях,
    если система уничтожит ваш процесс. По сути, это набор ключ-значений. Ограничение сохраняемых в Bundle данных = 1МБ.
    А вообще в Bundle не рекоммендуется сохранять более 50Кб информации.
    */
    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)

        outState?.putString("STATUS", benderObj.status.name)
        outState?.putString("QUESTION", benderObj.question.name)
        Log.d("M_MainActivity", "onSaveInstanceState ${benderObj.status.name} ${benderObj.question.name}")
    }

    override fun onClick(v: View?){
        if(v?.id == R.id.iv_send){
            val (phrase, color) = benderObj.listenAnswer(messageEt.text.toString().toLowerCase())
            messageEt.setText("")
            val (r,g,b) = color
            //добавление эффекта наложения
            benderImage.setColorFilter(Color.rgb(r,g,b), PorterDuff.Mode.MULTIPLY)
            //выведение нашего сообщения в textTxt
            textTxt.text = phrase
        }
    }
    /*fun onIvSendClicked(){
        val (phrase, color) = benderObj.listenAnswer(messageEt.text.toString().toLowerCase())
        messageEt.setText("")
        val (r,g,b) = color
        //добавление эффекта наложения
        benderImage.setColorFilter(Color.rgb(r,g,b), PorterDuff.Mode.MULTIPLY)
        //выведение нашего сообщения в textTxt
        textTxt.text = phrase
    }*/
}
