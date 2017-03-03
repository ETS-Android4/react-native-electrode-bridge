package com.walmartlabs.electrode.reactnative.sample.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.walmartlabs.electrode.reactnative.bridge.Bridgeable;

import static com.walmartlabs.electrode.reactnative.bridge.util.BridgeArguments.getNumberValue;

public class Position implements Parcelable, Bridgeable {

    private Double lat;
    private Double lng;

    private Position() {
    }

    private Position(Builder builder) {
        this.lat = builder.lat;
        this.lng = builder.lng;
    }

    private Position(Parcel in) {
        this(in.readBundle());
    }

    public Position(@NonNull Bundle bundle) {
        if (bundle.get("lat") == null) {
            throw new IllegalArgumentException("lat property is required");
        }
        this.lat = bundle.getDouble("lat");
        this.lng = bundle.containsKey("lng") ? bundle.getDouble("lng") : null;
    }

    public static final Creator<Position> CREATOR = new Creator<Position>() {
        @Override
        public Position createFromParcel(Parcel in) {
            return new Position(in);
        }

        @Override
        public Position[] newArray(int size) {
            return new Position[size];
        }
    };

    @NonNull
    public Double getLat() {
        return lat;
    }

    @Nullable
    public Double getLng() {
        return lng;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeBundle(toBundle());
    }

    @NonNull
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putDouble("lat", lat);
        if(lng != null) {
            bundle.putDouble("lng", lng);
        }
        return bundle;
    }

    public static class Builder {
        private final Double lat;
        private Double lng;

        public Builder(@NonNull Double lat) {
            this.lat = lat;
        }

        @NonNull
        public Builder lng(@Nullable Double lng) {
            this.lng = lng;
            return this;
        }

        @NonNull
        public Position build() {
            return new Position(this);
        }
    }
}