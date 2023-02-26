package com.example.movie_app.View.Ui.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.movie_app.R
import com.example.movie_app.Utils.Status
import com.example.movie_app.Utils.Status.*
import com.example.movie_app.databinding.FragmentDetailsBinding
import com.example.movie_app.viewModel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {


    lateinit var binding: FragmentDetailsBinding

    val args: DetailsFragmentArgs by navArgs()

    val viewModel: MovieViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backPress.setOnClickListener {
            findNavController().popBackStack()


            viewModel.getMovieDetails(args.imdbId!!)

            viewModel.movieDetails.observe(viewLifecycleOwner) {
                when (it.getContentIfNotHandled()?.status) {

                    LOADING -> {

                        binding.detailsProgress.visibility = View.VISIBLE

                    }
                    ERROR -> {
                        binding.detailsProgress.visibility = View.GONE
                    }
                    SUCCESS -> {
                        binding.detailsProgress.visibility = View.GONE

                        binding.movieDetails = it.peekContent().data

                    }
                    null -> TODO()
                }
            }
        }
    }

}