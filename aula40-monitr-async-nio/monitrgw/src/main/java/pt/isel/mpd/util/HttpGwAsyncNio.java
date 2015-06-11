/*
 * Copyright (C) 2015 Miguel Gamboa at CCISEL
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package pt.isel.mpd.util;

import com.ning.http.client.AsyncCompletionHandler;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 *
 * @author Miguel Gamboa at CCISEL
 */
public class HttpGwAsyncNio implements AutoCloseable{

    final AsyncHttpClient client;

    public HttpGwAsyncNio(AsyncHttpClient client) {
        this.client = client;
    }

    public HttpGwAsyncNio() {
        this(new AsyncHttpClient());
    }

    public CompletableFuture<Response> getDataAsync(String path){

        CompletableFuture<Response> promise = new CompletableFuture<>();
        client
                .prepareGet(path)
                .execute(new AsyncCompletionHandler<Object>() {
                    @Override
                    public Object onCompleted(Response response) throws Exception {
                        promise.complete(response);
                        return response;
                    }
                });
        return promise;
    }

    @Override
    public void close() throws IOException {
        if(!client.isClosed())
            client.close();
    }
}
