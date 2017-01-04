/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package de.s3xy.jokes.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import de.s3xy.CaptainFunny;
import de.s3xy.Joke;

/** An endpoint class we are exposing */
@Api(
        name = "jokesApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "jokes.s3xy4ngyc.de",
                ownerName = "jokes.s3xy4ngyc.de",
                packagePath = ""
        )
)
public class JokesEndpoint {


    @ApiMethod(name = "tellJoke")
    public Joke tellJoke() {
        return CaptainFunny.tellJoke();
    }

}
