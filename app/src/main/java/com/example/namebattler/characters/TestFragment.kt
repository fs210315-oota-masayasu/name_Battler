package com.example.namebattler.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.namebattler.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param_Name"
private const val ARG_PARAM2 = "param_Job"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [TestFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [TestFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TestFragment : Fragment() {

    private var paramName: String? = null
    private var paramJob: String? = null

    companion object {
        @JvmStatic
        fun newInstance(paramNmae: String?, paramJob: String?) =
            TestFragment().apply {
                arguments = Bundle().apply {
                    //putString(ARG_PARAM1, paramNmae)
                    //putString(ARG_PARAM2, paramJob)
                    putString(ARG_PARAM1, "TestName")
                    putString(ARG_PARAM2, "テスト職業")

                }
            }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramName = it.getString(ARG_PARAM1)
            paramJob = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test, container, false)
    }

    override fun onViewCreated(view :View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        val charaNameView : TextView = view.findViewById(R.id.value_name)
        val charaJobView : TextView = view.findViewById(R.id.value_job)
        val args : Bundle? = arguments

        val strName = args?.getString(ARG_PARAM1)
        val strJob = args?.getString(ARG_PARAM2)

        charaNameView.text = strName
        charaJobView.text = strJob

    }


}

/*

    companion object {
        @JvmStatic
        fun newInstance(paramNmae: String?, paramJob: String?) =
            TestFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, paramNmae)
                    putString(ARG_PARAM2, paramJob)
                }
            }
    }

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    */
/**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     *//*

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }
*/



/*
    companion object {
        */
/**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TestFragment.
         *//*

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TestFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
*/

