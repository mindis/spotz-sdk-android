package com.localz.spotz.api.v1;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpResponse;
import com.localz.spotz.api.ApiMethod;
import com.localz.spotz.api.exceptions.LocalzApiException;
import com.localz.spotz.api.models.Response;
import com.localz.spotz.api.models.request.v1.SpotzGetRequest;
import com.localz.spotz.api.models.response.v1.SpotzGetResponse;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class SpotzGetApi extends ApiMethod<SpotzGetRequest, SpotzGetResponse> {

    private static final String PATH = "/spotz/{spotzId}";

    @Override
    public Response<SpotzGetResponse> execute(SpotzGetRequest spotzId) throws LocalzApiException {
        try {
            String path = PATH.replace("{spotzId}", spotzId.spotzId);

            HttpResponse httpResponse = httpRequestFactory.buildGetRequest(
                    new GenericUrl(hostUrl + path))
                    .setHeaders(createDeviceSignedHeaders(new Date(), HttpMethods.GET, path))
                    .execute();

            return response(httpResponse, SpotzGetResponse.TYPE);

        } catch (IOException e) {
            throw new LocalzApiException("Exception while executing API request: " + SpotzGetApi.class.getSimpleName(), e);
        } catch (NoSuchAlgorithmException e) {
            throw new LocalzApiException("Exception while creating signature: ", e);
        } catch (InvalidKeyException e) {
            throw new LocalzApiException("Exception while creating signature: ", e);
        }
    }
}
