package com.example.spacex.utils

import com.example.spacex.data.network.api.ApiInterface
import com.example.spacex.data.network.response.LaunchResponseItem
import com.example.spacex.data.repository.SpaceXRepository
import com.example.spacex.viewmodel.SpaceXViewModel
import com.google.gson.Gson
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito


internal class SortAndFilterUtilsKtTest {
    private lateinit var viewModel: SpaceXViewModel
    private lateinit var spaceXRepository: SpaceXRepository
    private lateinit var apiServiceMock: ApiInterface
    private var launchItemOne = "{\n" +
            "    \"flight_number\": 1,\n" +
            "    \"mission_name\": \"FalconSat\",\n" +
            "    \"mission_id\": [\n" +
            "      \n" +
            "    ],\n" +
            "    \"upcoming\": false,\n" +
            "    \"launch_year\": \"2006\",\n" +
            "    \"launch_date_unix\": 1143239400,\n" +
            "    \"launch_date_utc\": \"2006-03-24T22:30:00.000Z\",\n" +
            "    \"launch_date_local\": \"2006-03-25T10:30:00+12:00\",\n" +
            "    \"is_tentative\": false,\n" +
            "    \"tentative_max_precision\": \"hour\",\n" +
            "    \"tbd\": false,\n" +
            "    \"launch_window\": 0,\n" +
            "    \"rocket\": {\n" +
            "      \"rocket_id\": \"falcon1\",\n" +
            "      \"rocket_name\": \"Falcon 1\",\n" +
            "      \"rocket_type\": \"Merlin A\",\n" +
            "      \"first_stage\": {\n" +
            "        \"cores\": [\n" +
            "          {\n" +
            "            \"core_serial\": \"Merlin1A\",\n" +
            "            \"flight\": 1,\n" +
            "            \"block\": null,\n" +
            "            \"gridfins\": false,\n" +
            "            \"legs\": false,\n" +
            "            \"reused\": false,\n" +
            "            \"land_success\": null,\n" +
            "            \"landing_intent\": false,\n" +
            "            \"landing_type\": null,\n" +
            "            \"landing_vehicle\": null\n" +
            "          }\n" +
            "        ]\n" +
            "      },\n" +
            "      \"second_stage\": {\n" +
            "        \"block\": 1,\n" +
            "        \"payloads\": [\n" +
            "          {\n" +
            "            \"payload_id\": \"FalconSAT-2\",\n" +
            "            \"norad_id\": [\n" +
            "              \n" +
            "            ],\n" +
            "            \"reused\": false,\n" +
            "            \"customers\": [\n" +
            "              \"DARPA\"\n" +
            "            ],\n" +
            "            \"nationality\": \"United States\",\n" +
            "            \"manufacturer\": \"SSTL\",\n" +
            "            \"payload_type\": \"Satellite\",\n" +
            "            \"payload_mass_kg\": 20,\n" +
            "            \"payload_mass_lbs\": 43,\n" +
            "            \"orbit\": \"LEO\",\n" +
            "            \"orbit_params\": {\n" +
            "              \"reference_system\": \"geocentric\",\n" +
            "              \"regime\": \"low-earth\",\n" +
            "              \"longitude\": null,\n" +
            "              \"semi_major_axis_km\": null,\n" +
            "              \"eccentricity\": null,\n" +
            "              \"periapsis_km\": 400,\n" +
            "              \"apoapsis_km\": 500,\n" +
            "              \"inclination_deg\": 39,\n" +
            "              \"period_min\": null,\n" +
            "              \"lifespan_years\": null,\n" +
            "              \"epoch\": null,\n" +
            "              \"mean_motion\": null,\n" +
            "              \"raan\": null,\n" +
            "              \"arg_of_pericenter\": null,\n" +
            "              \"mean_anomaly\": null\n" +
            "            }\n" +
            "          }\n" +
            "        ]\n" +
            "      },\n" +
            "      \"fairings\": {\n" +
            "        \"reused\": false,\n" +
            "        \"recovery_attempt\": false,\n" +
            "        \"recovered\": false,\n" +
            "        \"ship\": null\n" +
            "      }\n" +
            "    },\n" +
            "    \"ships\": [\n" +
            "      \n" +
            "    ],\n" +
            "    \"telemetry\": {\n" +
            "      \"flight_club\": null\n" +
            "    },\n" +
            "    \"launch_site\": {\n" +
            "      \"site_id\": \"kwajalein_atoll\",\n" +
            "      \"site_name\": \"Kwajalein Atoll\",\n" +
            "      \"site_name_long\": \"Kwajalein Atoll Omelek Island\"\n" +
            "    },\n" +
            "    \"launch_success\": true,\n" +
            "    \"launch_failure_details\": {\n" +
            "      \"time\": 33,\n" +
            "      \"altitude\": null,\n" +
            "      \"reason\": \"merlin engine failure\"\n" +
            "    },\n" +
            "    \"links\": {\n" +
            "      \"mission_patch\": \"https://images2.imgbox.com/40/e3/GypSkayF_o.png\",\n" +
            "      \"mission_patch_small\": \"https://images2.imgbox.com/3c/0e/T8iJcSN3_o.png\",\n" +
            "      \"reddit_campaign\": null,\n" +
            "      \"reddit_launch\": null,\n" +
            "      \"reddit_recovery\": null,\n" +
            "      \"reddit_media\": null,\n" +
            "      \"presskit\": null,\n" +
            "      \"article_link\": \"https://www.space.com/2196-spacex-inaugural-falcon-1-rocket-lost-launch.html\",\n" +
            "      \"wikipedia\": \"https://en.wikipedia.org/wiki/DemoSat\",\n" +
            "      \"video_link\": \"https://www.youtube.com/watch?v=0a_00nJ_Y88\",\n" +
            "      \"youtube_id\": \"0a_00nJ_Y88\",\n" +
            "      \"flickr_images\": [\n" +
            "        \n" +
            "      ]\n" +
            "    },\n" +
            "    \"details\": \"Engine failure at 33 seconds and loss of vehicle\",\n" +
            "    \"static_fire_date_utc\": \"2006-03-17T00:00:00.000Z\",\n" +
            "    \"static_fire_date_unix\": 1142553600,\n" +
            "    \"timeline\": {\n" +
            "      \"webcast_liftoff\": 54\n" +
            "    }\n" +
            "  }"

    private val launchItemSecond = "{\n" +
            "    \"flight_number\": 2,\n" +
            "    \"mission_name\": \"DemoSat\",\n" +
            "    \"mission_id\": [\n" +
            "      \n" +
            "    ],\n" +
            "    \"launch_year\": \"2007\",\n" +
            "    \"launch_date_unix\": 1174439400,\n" +
            "    \"launch_date_utc\": \"2007-03-21T01:10:00.000Z\",\n" +
            "    \"launch_date_local\": \"2007-03-21T13:10:00+12:00\",\n" +
            "    \"is_tentative\": false,\n" +
            "    \"tentative_max_precision\": \"hour\",\n" +
            "    \"tbd\": false,\n" +
            "    \"launch_window\": 0,\n" +
            "    \"rocket\": {\n" +
            "      \"rocket_id\": \"falcon1\",\n" +
            "      \"rocket_name\": \"Falcon 1\",\n" +
            "      \"rocket_type\": \"Merlin A\",\n" +
            "      \"first_stage\": {\n" +
            "        \"cores\": [\n" +
            "          {\n" +
            "            \"core_serial\": \"Merlin2A\",\n" +
            "            \"flight\": 1,\n" +
            "            \"block\": null,\n" +
            "            \"gridfins\": false,\n" +
            "            \"legs\": false,\n" +
            "            \"reused\": false,\n" +
            "            \"land_success\": null,\n" +
            "            \"landing_intent\": false,\n" +
            "            \"landing_type\": null,\n" +
            "            \"landing_vehicle\": null\n" +
            "          }\n" +
            "        ]\n" +
            "      },\n" +
            "      \"second_stage\": {\n" +
            "        \"block\": 1,\n" +
            "        \"payloads\": [\n" +
            "          {\n" +
            "            \"payload_id\": \"DemoSAT\",\n" +
            "            \"norad_id\": [\n" +
            "              \n" +
            "            ],\n" +
            "            \"reused\": false,\n" +
            "            \"customers\": [\n" +
            "              \"DARPA\"\n" +
            "            ],\n" +
            "            \"nationality\": \"United States\",\n" +
            "            \"manufacturer\": \"SpaceX\",\n" +
            "            \"payload_type\": \"Satellite\",\n" +
            "            \"payload_mass_kg\": null,\n" +
            "            \"payload_mass_lbs\": null,\n" +
            "            \"orbit\": \"LEO\",\n" +
            "            \"orbit_params\": {\n" +
            "              \"reference_system\": \"geocentric\",\n" +
            "              \"regime\": \"low-earth\",\n" +
            "              \"longitude\": null,\n" +
            "              \"semi_major_axis_km\": null,\n" +
            "              \"eccentricity\": null,\n" +
            "              \"periapsis_km\": null,\n" +
            "              \"apoapsis_km\": null,\n" +
            "              \"inclination_deg\": null,\n" +
            "              \"period_min\": null,\n" +
            "              \"lifespan_years\": null,\n" +
            "              \"epoch\": null,\n" +
            "              \"mean_motion\": null,\n" +
            "              \"raan\": null,\n" +
            "              \"arg_of_pericenter\": null,\n" +
            "              \"mean_anomaly\": null\n" +
            "            }\n" +
            "          }\n" +
            "        ]\n" +
            "      },\n" +
            "      \"fairings\": {\n" +
            "        \"reused\": false,\n" +
            "        \"recovery_attempt\": false,\n" +
            "        \"recovered\": false,\n" +
            "        \"ship\": null\n" +
            "      }\n" +
            "    },\n" +
            "    \"ships\": [\n" +
            "      \n" +
            "    ],\n" +
            "    \"telemetry\": {\n" +
            "      \"flight_club\": null\n" +
            "    },\n" +
            "    \"launch_site\": {\n" +
            "      \"site_id\": \"kwajalein_atoll\",\n" +
            "      \"site_name\": \"Kwajalein Atoll\",\n" +
            "      \"site_name_long\": \"Kwajalein Atoll Omelek Island\"\n" +
            "    },\n" +
            "    \"launch_success\": false,\n" +
            "    \"launch_failure_details\": {\n" +
            "      \"time\": 301,\n" +
            "      \"altitude\": 289,\n" +
            "      \"reason\": \"harmonic oscillation leading to premature engine shutdown\"\n" +
            "    },\n" +
            "    \"links\": {\n" +
            "      \"mission_patch\": \"https://images2.imgbox.com/be/e7/iNqsqVYM_o.png\",\n" +
            "      \"mission_patch_small\": \"https://images2.imgbox.com/4f/e3/I0lkuJ2e_o.png\",\n" +
            "      \"reddit_campaign\": null,\n" +
            "      \"reddit_launch\": null,\n" +
            "      \"reddit_recovery\": null,\n" +
            "      \"reddit_media\": null,\n" +
            "      \"presskit\": null,\n" +
            "      \"article_link\": \"https://www.space.com/3590-spacex-falcon-1-rocket-fails-reach-orbit.html\",\n" +
            "      \"wikipedia\": \"https://en.wikipedia.org/wiki/DemoSat\",\n" +
            "      \"video_link\": \"https://www.youtube.com/watch?v=Lk4zQ2wP-Nc\",\n" +
            "      \"youtube_id\": \"Lk4zQ2wP-Nc\",\n" +
            "      \"flickr_images\": [\n" +
            "        \n" +
            "      ]\n" +
            "    },\n" +
            "    \"details\": \"Successful first stage burn and transition to second stage, maximum altitude 289 km, Premature engine shutdown at T+7 min 30 s, Failed to reach orbit, Failed to recover first stage\",\n" +
            "    \"upcoming\": false,\n" +
            "    \"static_fire_date_utc\": null,\n" +
            "    \"static_fire_date_unix\": null,\n" +
            "    \"timeline\": {\n" +
            "      \"webcast_liftoff\": 60\n" +
            "    }\n" +
            "  }"


    @Before
    fun initialize() {
        apiServiceMock = Mockito.mock(ApiInterface::class.java)
        spaceXRepository = SpaceXRepository(apiServiceMock)
        viewModel = SpaceXViewModel(spaceXRepository)
        viewModel.launchList = arrayListOf(
            Gson().fromJson(launchItemOne, LaunchResponseItem::class.java),
            Gson().fromJson(launchItemSecond, LaunchResponseItem::class.java)
        )
    }


    @Test
    fun sortByMissionNameTest() {
        Assert.assertEquals(
            "DemoSat",
            (sortByMissionName(viewModel.launchList))[0].missionName
        )
    }

    @Test
    fun filterByLaunchSuccess() {
        Assert.assertEquals(
            true,
            (filterByLaunchSuccess(viewModel.launchList))[0].launchSuccess
        )
    }

    @Test
    fun filterByLaunchUnSuccess() {
        Assert.assertEquals(
            false,
            (filterByLaunchUnSuccess(viewModel.launchList))[0].launchSuccess
        )
    }
}