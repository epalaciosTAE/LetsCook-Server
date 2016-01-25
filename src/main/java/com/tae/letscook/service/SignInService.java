package com.tae.letscook.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.tae.letscook.constants.Constants;
import com.tae.letscook.model.edaman.Chef;

@Service("SignInService")
public class SignInService {

	public Chef authorizeUser(String authcode) {
		Chef chef = null;
		try{
			// Exchange auth code for access token
			GoogleClientSecrets clientSecrets = getGoogleClientSecrets();
			
			GoogleTokenResponse tokenResponse = getGoogleTokenResponse(authcode, clientSecrets);

			String accessToken = tokenResponse.getAccessToken();
			System.out.println("Login Service - Access Token " + accessToken);

			// Use access token to call API
//			GoogleCredential credential = new GoogleCredential().setAccessToken(accessToken);
//			Drive drive =
//			    new Drive.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance(), credential)
//			        .setApplicationName("Auth Code Exchange Demo")
//			        .build();
//			File file = drive.files().get("appfolder").execute();

			chef = generateProfile(tokenResponse);
		} catch (IOException e){
			System.out.println("Login Service - Error authorizing user " + e);
		}
		return chef;
	}

	private Chef generateProfile(GoogleTokenResponse tokenResponse) throws IOException {
		Chef chef;
		// Get profile info from ID token
		GoogleIdToken idToken = tokenResponse.parseIdToken();
		GoogleIdToken.Payload payload = idToken.getPayload();
		String userId = payload.getSubject();  // Use this value as a key to identify a user.
		String email = payload.getEmail();
		boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
		String name = (String) payload.get("name");
		String pictureUrl = (String) payload.get("picture");
		String locale = (String) payload.get("locale");
		String familyName = (String) payload.get("family_name");
		String givenName = (String) payload.get("given_name");
		chef = new Chef(name, email, userId, emailVerified, pictureUrl, locale, familyName, givenName);
		return chef;
	}

	private GoogleClientSecrets getGoogleClientSecrets() throws IOException, FileNotFoundException {
		return GoogleClientSecrets.load(JacksonFactory.getDefaultInstance(), new FileReader(Constants.CLIENT_SECRET));
	}

	private GoogleTokenResponse getGoogleTokenResponse(String authcode, GoogleClientSecrets clientSecrets) throws IOException {
		return new GoogleAuthorizationCodeTokenRequest(
		              new NetHttpTransport(),
		              JacksonFactory.getDefaultInstance(),
		              "https://www.googleapis.com/oauth2/v4/token", //https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=XYZ123
		              clientSecrets.getDetails().getClientId(),
		              clientSecrets.getDetails().getClientSecret(),
		              authcode,
		              "")  //REDIRECT_URI--> Specify the same redirect URI that you use with your web
		                             // app. If you don't have a web version of your app, you can
		                             // specify an empty string.
		              .execute();
	}
	
}
