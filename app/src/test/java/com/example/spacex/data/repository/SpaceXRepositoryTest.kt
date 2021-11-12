
package com.example.spacex.data.repository

import com.example.spacex.data.network.api.ApiInterface
import com.example.spacex.data.network.response.RocketResponse
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test
import org.mockito.Mockito
import retrofit2.Response

internal class SpaceXRepositoryTest {
    private lateinit var apiServiceMock: ApiInterface
    private lateinit var rocketResponse: RocketResponse
    private val request = "{\n" +
            "  \"id\": 1,\n" +
            "  \"active\": false,\n" +
            "  \"stages\": 2,\n" +
            "  \"boosters\": 0,\n" +
            "  \"cost_per_launch\": 6700000,\n" +
            "  \"success_rate_pct\": 40,\n" +
            "  \"first_flight\": \"2006-03-24\",\n" +
            "  \"country\": \"Republic of the Marshall Islands\",\n" +
            "  \"company\": \"SpaceX\",\n" +
            "  \"height\": {\n" +
            "    \"meters\": 22.25,\n" +
            "    \"feet\": 73\n" +
            "  },\n" +
            "  \"diameter\": {\n" +
            "    \"meters\": 1.68,\n" +
            "    \"feet\": 5.5\n" +
            "  },\n" +
            "  \"mass\": {\n" +
            "    \"kg\": 30146,\n" +
            "    \"lb\": 66460\n" +
            "  },\n" +
            "  \"payload_weights\": [\n" +
            "    {\n" +
            "      \"id\": \"leo\",\n" +
            "      \"name\": \"Low Earth Orbit\",\n" +
            "      \"kg\": 450,\n" +
            "      \"lb\": 992\n" +
            "    }\n" +
            "  ],\n" +
            "  \"first_stage\": {\n" +
            "    \"reusable\": false,\n" +
            "    \"engines\": 1,\n" +
            "    \"fuel_amount_tons\": 44.3,\n" +
            "    \"burn_time_sec\": 169,\n" +
            "    \"thrust_sea_level\": {\n" +
            "      \"kN\": 420,\n" +
            "      \"lbf\": 94000\n" +
            "    },\n" +
            "    \"thrust_vacuum\": {\n" +
            "      \"kN\": 480,\n" +
            "      \"lbf\": 110000\n" +
            "    }\n" +
            "  },\n" +
            "  \"second_stage\": {\n" +
            "    \"reusable\": false,\n" +
            "    \"engines\": 1,\n" +
            "    \"fuel_amount_tons\": 3.38,\n" +
            "    \"burn_time_sec\": 378,\n" +
            "    \"thrust\": {\n" +
            "      \"kN\": 31,\n" +
            "      \"lbf\": 7000\n" +
            "    },\n" +
            "    \"payloads\": {\n" +
            "      \"option_1\": \"composite fairing\",\n" +
            "      \"composite_fairing\": {\n" +
            "        \"height\": {\n" +
            "          \"meters\": 3.5,\n" +
            "          \"feet\": 11.5\n" +
            "        },\n" +
            "        \"diameter\": {\n" +
            "          \"meters\": 1.5,\n" +
            "          \"feet\": 4.9\n" +
            "        }\n" +
            "      }\n" +
            "    }\n" +
            "  },\n" +
            "  \"engines\": {\n" +
            "    \"number\": 1,\n" +
            "    \"type\": \"merlin\",\n" +
            "    \"version\": \"1C\",\n" +
            "    \"layout\": \"single\",\n" +
            "    \"isp\": {\n" +
            "      \"sea_level\": 267,\n" +
            "      \"vacuum\": 304\n" +
            "    },\n" +
            "    \"engine_loss_max\": 0,\n" +
            "    \"propellant_1\": \"liquid oxygen\",\n" +
            "    \"propellant_2\": \"RP-1 kerosene\",\n" +
            "    \"thrust_sea_level\": {\n" +
            "      \"kN\": 420,\n" +
            "      \"lbf\": 94000\n" +
            "    },\n" +
            "    \"thrust_vacuum\": {\n" +
            "      \"kN\": 480,\n" +
            "      \"lbf\": 110000\n" +
            "    },\n" +
            "    \"thrust_to_weight\": 96\n" +
            "  },\n" +
            "  \"landing_legs\": {\n" +
            "    \"number\": 0,\n" +
            "    \"material\": null\n" +
            "  },\n" +
            "  \"flickr_images\": [\n" +
            "    \"https://imgur.com/DaCfMsj.jpg\",\n" +
            "    \"https://imgur.com/azYafd8.jpg\"\n" +
            "  ],\n" +
            "  \"wikipedia\": \"https://en.wikipedia.org/wiki/Falcon_1\",\n" +
            "  \"description\": \"The Falcon 1 was an expendable launch system privately developed and manufactured by SpaceX during 2006-2009. On 28 September 2008, Falcon 1 became the first privately-developed liquid-fuel launch vehicle to go into orbit around the Earth.\",\n" +
            "  \"rocket_id\": \"falcon1\",\n" +
            "  \"rocket_name\": \"Falcon 1\",\n" +
            "  \"rocket_type\": \"rocket\"\n" +
            "}"

    @BeforeEach
    fun initialize() {
        apiServiceMock = Mockito.mock(ApiInterface::class.java)
        rocketResponse = Gson().fromJson(request, RocketResponse::class.java)

    }

    @Test
    fun getRocketInfo() {
        runBlocking {
            Mockito.`when`(apiServiceMock.getRocketInfo("falcon1"))
                .thenReturn(
                    Response.success(
                        200,
                        rocketResponse
                    )
                )
            Assert.assertEquals(
                "should return the status code 200",
                "200",
                SpaceXRepository(apiServiceMock).getRocketInfo(
                    "falcon1"
                ).isSuccessful
            )
        }
    }

}
