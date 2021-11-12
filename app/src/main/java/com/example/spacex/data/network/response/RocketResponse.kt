package com.example.spacex.data.network.response


import com.google.gson.annotations.SerializedName

data class RocketResponse(
    @SerializedName("active")
    val active: Boolean?,
    @SerializedName("boosters")
    val boosters: Double?,
    @SerializedName("company")
    val company: String?,
    @SerializedName("cost_per_launch")
    val costPerLaunch: Double?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("diameter")
    val diameter: Diameter?,
    @SerializedName("engines")
    val engines: Engines?,
    @SerializedName("first_flight")
    val firstFlight: String?,
    @SerializedName("first_stage")
    val firstStage: FirstStage?,
    @SerializedName("flickr_images")
    val flickrImages: List<String?>?,
    @SerializedName("height")
    val height: Height?,
    @SerializedName("id")
    val id: Double?,
    @SerializedName("landing_legs")
    val landingLegs: LandingLegs?,
    @SerializedName("mass")
    val mass: Mass?,
    @SerializedName("payload_weights")
    val payloadWeights: List<PayloadWeight?>?,
    @SerializedName("rocket_id")
    val rocketId: String?,
    @SerializedName("rocket_name")
    val rocketName: String?,
    @SerializedName("rocket_type")
    val rocketType: String?,
    @SerializedName("second_stage")
    val secondStage: SecondStage?,
    @SerializedName("stages")
    val stages: Double?,
    @SerializedName("success_rate_pct")
    val successRatePct: Double?,
    @SerializedName("wikipedia")
    val wikipedia: String?
) {
    data class Diameter(
        @SerializedName("feet")
        val feet: Double?,
        @SerializedName("meters")
        val meters: Double?
    )

    data class Engines(
        @SerializedName("engine_loss_max")
        val engineLossMax: Double?,
        @SerializedName("isp")
        val isp: Isp?,
        @SerializedName("layout")
        val layout: String?,
        @SerializedName("number")
        val number: Double?,
        @SerializedName("propellant_1")
        val propellant1: String?,
        @SerializedName("propellant_2")
        val propellant2: String?,
        @SerializedName("thrust_sea_level")
        val thrustSeaLevel: ThrustSeaLevel?,
        @SerializedName("thrust_to_weight")
        val thrustToWeight: Double?,
        @SerializedName("thrust_vacuum")
        val thrustVacuum: ThrustVacuum?,
        @SerializedName("type")
        val type: String?,
        @SerializedName("version")
        val version: String?
    ) {
        data class Isp(
            @SerializedName("sea_level")
            val seaLevel: Double?,
            @SerializedName("vacuum")
            val vacuum: Double?
        )

        data class ThrustSeaLevel(
            @SerializedName("kN")
            val kN: Double?,
            @SerializedName("lbf")
            val lbf: Double?
        )

        data class ThrustVacuum(
            @SerializedName("kN")
            val kN: Double?,
            @SerializedName("lbf")
            val lbf: Double?
        )
    }

    data class FirstStage(
        @SerializedName("burn_time_sec")
        val burnTimeSec: Double?,
        @SerializedName("engines")
        val engines: Double?,
        @SerializedName("fuel_amount_tons")
        val fuelAmountTons: Double?,
        @SerializedName("reusable")
        val reusable: Boolean?,
        @SerializedName("thrust_sea_level")
        val thrustSeaLevel: ThrustSeaLevel?,
        @SerializedName("thrust_vacuum")
        val thrustVacuum: ThrustVacuum?
    ) {
        data class ThrustSeaLevel(
            @SerializedName("kN")
            val kN: Double?,
            @SerializedName("lbf")
            val lbf: Double?
        )

        data class ThrustVacuum(
            @SerializedName("kN")
            val kN: Double?,
            @SerializedName("lbf")
            val lbf: Double?
        )
    }

    data class Height(
        @SerializedName("feet")
        val feet: Double?,
        @SerializedName("meters")
        val meters: Double?
    )

    data class LandingLegs(
        @SerializedName("material")
        val material: String?,
        @SerializedName("number")
        val number: Double?
    )

    data class Mass(
        @SerializedName("kg")
        val kg: Double?,
        @SerializedName("lb")
        val lb: Double?
    )

    data class PayloadWeight(
        @SerializedName("id")
        val id: String?,
        @SerializedName("kg")
        val kg: Double?,
        @SerializedName("lb")
        val lb: Double?,
        @SerializedName("name")
        val name: String?
    )

    data class SecondStage(
        @SerializedName("burn_time_sec")
        val burnTimeSec: Double?,
        @SerializedName("engines")
        val engines: Double?,
        @SerializedName("fuel_amount_tons")
        val fuelAmountTons: Double?,
        @SerializedName("payloads")
        val payloads: Payloads?,
        @SerializedName("reusable")
        val reusable: Boolean?,
        @SerializedName("thrust")
        val thrust: Thrust?
    ) {
        data class Payloads(
            @SerializedName("composite_fairing")
            val compositeFairing: CompositeFairing?,
            @SerializedName("option_1")
            val option1: String?,
            @SerializedName("option_2")
            val option2: String?
        ) {
            data class CompositeFairing(
                @SerializedName("diameter")
                val diameter: Diameter?,
                @SerializedName("height")
                val height: Height?
            ) {
                data class Diameter(
                    @SerializedName("feet")
                    val feet: Double?,
                    @SerializedName("meters")
                    val meters: Double?
                )

                data class Height(
                    @SerializedName("feet")
                    val feet: Double?,
                    @SerializedName("meters")
                    val meters: Double?
                )
            }
        }

        data class Thrust(
            @SerializedName("kN")
            val kN: Double?,
            @SerializedName("lbf")
            val lbf: Double?
        )
    }
}