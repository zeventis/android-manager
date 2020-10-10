package br.com.zeventis.managerapp.data.api

import br.com.zeventis.managerapp.core.utils.Constants
import br.com.zeventis.managerapp.data.model.addevent.AddEventRequest
import br.com.zeventis.managerapp.data.model.addevent.AddEventResponse
import br.com.zeventis.managerapp.data.model.authentication.LoginRequest
import br.com.zeventis.managerapp.data.model.authentication.UserResponse
import br.com.zeventis.managerapp.data.model.eventdetail.EventDetailResponse
import br.com.zeventis.managerapp.data.model.home.HomeEventsResponse
import br.com.zeventis.managerapp.data.model.profile.ProducerProfileRequest
import br.com.zeventis.managerapp.data.model.profile.ProducerProfileResponse
import br.com.zeventis.managerapp.data.model.register.CompanyRegisterSearchResponse
import br.com.zeventis.managerapp.data.model.register.RegisterRequest
import br.com.zeventis.managerapp.data.model.requesteventpromoter.RequestPromoterEventRequest
import br.com.zeventis.managerapp.data.model.updateevent.EventUpdateRequest
import br.com.zeventis.managerapp.data.model.updateevent.EventUpdateResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface IApiCore {
    @POST(Constants.Router.AUTHENTICATION)
    suspend fun doLogin(@Body loginRequest: LoginRequest): UserResponse

    @POST(Constants.Router.REGISTER)
    suspend fun register(@Body loginRequest: RegisterRequest): UserResponse

    @GET(Constants.Router.GET_COMPANY_BY_NAME)
    suspend fun getCompanyByName(@Path("name") companyName: String): CompanyRegisterSearchResponse

    @GET(Constants.Router.GET_PROFILE)
    suspend fun getProducerProfile(): ProducerProfileResponse

    @PUT(Constants.Router.UPDATE_PROFILE)
    suspend fun updateProfile(@Body profileRequest: ProducerProfileRequest)

    @GET(Constants.Router.GET_EVENTS_HOME)
    suspend fun getEventsHome(): List<HomeEventsResponse>

    @POST(Constants.Router.ADD_EVENT)
    suspend fun addEvent(@Body addEventRequest: AddEventRequest): AddEventResponse

    @POST(Constants.Router.GET_EVENT_DETAIL)
    suspend fun getEventDetail(@Path("eventId") eventId: String): EventDetailResponse

    @POST(Constants.Router.UPDATE_EVENT)
    suspend fun getEventDetail(
        @Path("eventId") eventId: String,
        @Body eventUpdateRequest: EventUpdateRequest
    ): EventUpdateResponse

    @POST(Constants.Router.REQUEST_PROMOTER_TO_EVENT)
    suspend fun requestPromoterToEvent(
        @Path("eventId") eventId: String,
        @Path("promoterId") promoterId: String,
        @Body requestPromoterEventRequest: RequestPromoterEventRequest
    )

    @POST(Constants.Router.DOUBLE_CHECK_PROMOTER_EVENT)
    suspend fun doubleCheckPromoterToEvent(
        @Path("eventId") eventId: String,
        @Path("promoterId") promoterId: String
    )
}