package io.codenetics.flex.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.List;

/**
 * Created by Pavel Laktiushkin on 29.09.2019
 */
public class GraphQLRequestError extends RuntimeException implements GraphQLError {
    public GraphQLRequestError(String message) {
        super(message);
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return null;
    }
}
