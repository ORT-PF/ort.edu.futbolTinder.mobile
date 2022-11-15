package ort.edu.ar.futboltinder.domain.Match.Forms

import android.os.Parcel
import android.os.Parcelable

data class MatchCreatorForm(
    val fieldName: String?,
    val originalQuota: Int,
    val fieldAddress: String?,
    val dateTime: String?,
    val longitude: Double?,
    val latitude: Double?
) : Parcelable{

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readDouble()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(fieldName)
        parcel.writeInt(originalQuota)
        parcel.writeString(fieldAddress)
        parcel.writeString(dateTime)
        parcel.writeDouble(longitude!!)
        parcel.writeDouble(latitude!!)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MatchCreatorForm> {
        override fun createFromParcel(parcel: Parcel): MatchCreatorForm {
            return MatchCreatorForm(parcel)
        }

        override fun newArray(size: Int): Array<MatchCreatorForm?> {
            return arrayOfNulls(size)
        }
    }
}
