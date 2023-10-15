package com.example.animationlab

import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.Fade
import android.transition.Scene
import android.transition.TransitionManager
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class MainActivity: AppCompatActivity() {
    private var editTextName1: EditText? = null //scene1
    private var editTextName2: EditText? = null //scene2
    private var editTextAddr1: EditText? = null //scene1
    private var editTextAddr2: EditText? = null //scene2
    private var checkSceneRadioButton = -1 //미선택

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sceneRoot = findViewById<FrameLayout>(R.id.scene_root)
        val scene1 = Scene.getSceneForLayout(sceneRoot, R.layout.scene_1, this)
        val scene2 = Scene.getSceneForLayout(sceneRoot, R.layout.scene_2, this)

        //라디오 버튼 클릭시 화면 전환
        val radioGroup = findViewById<RadioGroup>(R.id.RadioGroup)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioStudent -> {
                    //scene2 데이터 저장
                    editTextName2 = scene2.sceneRoot.findViewById<EditText>(R.id.editTextName)
                    editTextAddr2 = scene2.sceneRoot.findViewById<EditText>(R.id.editTextAddr)
                    TransitionManager.go(scene1, ChangeBounds())
                    //scene1 화면 세팅
                    //라디오 버튼
                    when(checkSceneRadioButton) {
                        -1 -> {}
                        1 -> findViewById<RadioButton>(R.id.radioButton1).isChecked = true
                        2 -> findViewById<RadioButton>(R.id.radioButton2).isChecked = true
                        3 -> findViewById<RadioButton>(R.id.radioButton3).isChecked = true
                        4 -> findViewById<RadioButton>(R.id.radioButton4).isChecked = true
                    }
                    changeRadio()

                    //EditText
                    if (editTextName1?.text?.isNotEmpty() == true)
                        scene1.sceneRoot.findViewById<EditText>(R.id.editTextName).text = editTextName1!!.text
                    if(editTextAddr1?.text?.isNotEmpty() == true)
                        scene1.sceneRoot.findViewById<EditText>(R.id.editTextAddr).text = editTextAddr1!!.text
                }
                R.id.radioStaff -> {
                    //scene1
                    //초기 데이터 저장
                    editTextName1 = scene1.sceneRoot.findViewById<EditText>(R.id.editTextName)
                    editTextAddr1 = scene1.sceneRoot.findViewById<EditText>(R.id.editTextAddr)
                    //전환
                    TransitionManager.go(scene2, Fade())
                    //scene2
                    //저장된 데이터 출력

                    //EditText
                    if (editTextName2?.text?.isNotEmpty() == true)
                        scene2.sceneRoot.findViewById<EditText>(R.id.editTextName).text = editTextName2!!.text
                    if(editTextAddr2?.text?.isNotEmpty() == true)
                        scene2.sceneRoot.findViewById<EditText>(R.id.editTextAddr).text = editTextAddr2!!.text
                }
            }
        }

    }

    //scene1에서 선택한 라디오 변수 저장
    private fun changeRadio() {
        val sceneRadioGroup = findViewById<RadioGroup>(R.id.sceneRadioGroup)
        sceneRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId) {
                R.id.radioButton1 -> checkSceneRadioButton = 1
                R.id.radioButton2 -> checkSceneRadioButton = 2
                R.id.radioButton3 -> checkSceneRadioButton = 3
                R.id.radioButton4 -> checkSceneRadioButton = 4
            }
        }
    }
}

