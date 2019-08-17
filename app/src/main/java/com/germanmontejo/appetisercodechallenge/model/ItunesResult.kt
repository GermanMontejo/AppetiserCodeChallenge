import com.google.gson.annotations.SerializedName

/*
Copyright (c) 2019 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


data class ItunesResult (

	@SerializedName("wrapperType") val wrapperType : String,
	@SerializedName("kind") val kind : String,
	@SerializedName("artistId") val artistId : Int,
	@SerializedName("collectionId") val collectionId : Int,
	@SerializedName("trackId") val trackId : Int,
	@SerializedName("artistName") val artistName : String,
	@SerializedName("collectionName") val collectionName : String,
	@SerializedName("trackName") val trackName : String,
	@SerializedName("collectionCensoredName") val collectionCensoredName : String,
	@SerializedName("trackCensoredName") val trackCensoredName : String,
	@SerializedName("artistViewUrl") val artistViewUrl : String,
	@SerializedName("collectionViewUrl") val collectionViewUrl : String,
	@SerializedName("trackViewUrl") val trackViewUrl : String,
	@SerializedName("previewUrl") val previewUrl : String,
	@SerializedName("artworkUrl30") val artworkUrl30 : String,
	@SerializedName("artworkUrl60") val artworkUrl60 : String,
	@SerializedName("artworkUrl100") val artworkUrl100 : String,
	@SerializedName("collectionPrice") val collectionPrice : Double,
	@SerializedName("trackPrice") val trackPrice : Double,
	@SerializedName("releaseDate") val releaseDate : String,
	@SerializedName("collectionExplicitness") val collectionExplicitness : String,
	@SerializedName("trackExplicitness") val trackExplicitness : String,
	@SerializedName("discCount") val discCount : Int,
	@SerializedName("discNumber") val discNumber : Int,
	@SerializedName("trackCount") val trackCount : Int,
	@SerializedName("trackNumber") val trackNumber : Int,
	@SerializedName("trackTimeMillis") val trackTimeMillis : Int,
	@SerializedName("country") val country : String,
	@SerializedName("currency") val currency : String,
	@SerializedName("primaryGenreName") val primaryGenreName : String,
	@SerializedName("isStreamable") val isStreamable : Boolean,
	@SerializedName("shortDescription") val shortDescription: String,
	@SerializedName("longDescription") val longDescription: String
)