package ort.edu.ar.futboltinder.domain.Match.viewModels

//REVISAR SI SE PUEDE BORRAR -FEDE

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class matchViewModel(val fieldName: String?, val fieldAddress: String?, val originalQuota: Int,
    val longitude: Double?,val latitude: Double?) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(fieldName)
        parcel.writeString(fieldAddress)
        parcel.writeInt(originalQuota)
        parcel.writeValue(longitude)
        parcel.writeValue(latitude)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<matchViewModel> {
        override fun createFromParcel(parcel: Parcel): matchViewModel {
            return matchViewModel(parcel)
        }

        override fun newArray(size: Int): Array<matchViewModel?> {
            return arrayOfNulls(size)
        }
    }


}

