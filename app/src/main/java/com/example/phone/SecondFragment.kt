package com.example.phone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.phone.local.RealDetailsPhone
import com.example.phone.viewModel.DetailsViewModel
import kotlinx.android.synthetic.main.fragment_second.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    lateinit var mViewModel2: DetailsViewModel
    lateinit var details: RealDetailsPhone
    var mId2: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel2 = ViewModelProvider(this).get(DetailsViewModel::class.java)
        arguments.let {
            mId2 = (arguments?.getString("id") ?: 0) as Int
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel2.obtainPhoneByID(mId2).observe(viewLifecycleOwner, Observer {
            if (it != null) {
                Glide.with(this).load(it.image).into(imageView2)

                tv5.text = it.description
                tv6.text = it.lastPrice.toString()
                tv7.text = it.name
                tv8.text = it.price.toString()


            }
        })


        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }
}

