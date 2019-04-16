package com.example.fragmentcontrol


import android.graphics.Color.BLUE
import android.graphics.Color.WHITE
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FragmentInput : Fragment()
{
    var button: Button? = null
    var button3: Button? = null
    var button4: Button? = null
    var edit1: EditText? = null
    var edit2: EditText? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        var main_activity = activity as MainActivity
        var view = inflater!!.inflate(R.layout.fragment_fragment_input, container, false)

        main_activity.button_Left_Color = BLUE
        main_activity.button_Right_Color = WHITE

        main_activity.button_Text_Left = "첫 번째 화면 실행중"
        main_activity.button_Text_Right = "두 번째 화면 "

        button = view.findViewById<Button>(R.id.button)

        edit1 = view.findViewById<EditText>(R.id.editText)
        edit2 = view.findViewById<EditText>(R.id.editText2)

        button?.setOnClickListener { view ->
            main_activity.value1 = edit1?.text.toString()
            main_activity.value2 = edit2?.text.toString()
            main_activity.setFragment("result")
        }
        return view
    }
}
