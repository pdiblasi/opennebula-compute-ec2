/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jclouds.opennebula.compute.ec2;

import java.net.URI;
import java.util.Properties;

import org.jclouds.apis.ApiMetadata;

//import org.jclouds.ec2.EC2Api;
import org.jclouds.ec2.EC2ApiMetadata;
import org.jclouds.ec2.compute.EC2ComputeServiceContext;
import org.jclouds.ec2.compute.config.EC2ResolveImagesModule;
import org.jclouds.ec2.compute.config.EC2ComputeServiceContextModule;
//import org.jclouds.ec2.config.EC2HttpApiModule;
import org.jclouds.rest.internal.BaseHttpApiMetadata;

import static org.jclouds.location.reference.LocationConstants.PROPERTY_REGIONS;
//import org.jclouds.opennebula.compute.ec2.Region;

import com.google.auto.service.AutoService;
import com.google.common.collect.ImmutableSet;
import com.google.inject.Module;


@AutoService(ApiMetadata.class)
public final class OpenNebulaComputeEC2ApiMetadata extends BaseHttpApiMetadata<OpenNebulaComputeEC2Api> {
    
   @Override
   public Builder toBuilder() {
      return new Builder().fromApiMetadata(this);
   }

   public OpenNebulaComputeEC2ApiMetadata() {
      super(new Builder());
   }

   protected OpenNebulaComputeEC2ApiMetadata(Builder builder) {
      super(builder);
   }

   public static Properties defaultProperties() {
      Properties properties = EC2ApiMetadata.defaultProperties();
      properties.setProperty(PROPERTY_REGIONS, "opennebula");
//      properties.putAll(Region.regionProperties());
      
      return properties;
   }

   public static final class Builder extends BaseHttpApiMetadata.Builder<OpenNebulaComputeEC2Api, Builder> {
      public Builder() {
         id("opennebula-compute-ec2")
         .name("OpenNebula Compute EC2 API")
         .version("2016-01-13")
         .identityName("Access Key ID")
         .credentialName("Secret Access Key")
         .defaultEndpoint("http://localhost:4567/")
         .documentation(URI.create("http://docs.amazonwebservices.com/AWSEC2/latest/APIReference"))
         .defaultProperties(OpenNebulaComputeEC2ApiMetadata.defaultProperties())
         .view(EC2ComputeServiceContext.class)
         .defaultModules(ImmutableSet.<Class<? extends Module>>builder()
                                     .add(OpenNebulaComputeEC2HttpApiModule.class)
                                     .add(EC2ResolveImagesModule.class)
                                     .add(EC2ComputeServiceContextModule.class).build());
      }

      @Override
      public OpenNebulaComputeEC2ApiMetadata build() {
         return new OpenNebulaComputeEC2ApiMetadata(this);
      }

      @Override
      protected Builder self() {
         return this;
      } 
   }
}
