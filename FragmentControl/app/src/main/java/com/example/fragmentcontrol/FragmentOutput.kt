package com.example.fragmentcontrol


import android.graphics.Color.BLUE
import android.graphics.Color.WHITE
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FragmentOutput : Fragment()
{

    var button2: Button? = null
    var textView: TextView? = null
    var textView2: TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        var main_activity = activity as MainActivity
        var view = inflater!!.inflate(R.layout.fragment_fragment_output, container, false)

        main_activity.button_Left_Color = WHITE
        main_activity.button_Right_Color = BLUE

        main_activity.button_Text_Left = "첫 번째 화면"
        main_activity.button_Text_Right = "두 번째 화면 실행중"

        button2 = view.findViewById(R.id.button2)

        textView = view.findViewById<TextView>(R.id.textView)
        textView2 = view.findViewById<TextView>(R.id.textView2)

        textView?.text = main_activity.value1
        textView2?.text = main_activity.value2

        button2?.setOnClickListener { view ->
            main_activity.supportFragmentManager.popBackStack()
        }
        return view
    }
}
