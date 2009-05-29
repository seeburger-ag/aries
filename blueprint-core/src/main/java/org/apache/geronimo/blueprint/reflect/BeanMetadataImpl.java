/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.geronimo.blueprint.reflect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.geronimo.blueprint.mutable.MutableBeanMetadata;
import org.osgi.service.blueprint.reflect.BeanArgument;
import org.osgi.service.blueprint.reflect.BeanMetadata;
import org.osgi.service.blueprint.reflect.BeanProperty;
import org.osgi.service.blueprint.reflect.Metadata;
import org.osgi.service.blueprint.reflect.Target;

/**
 * Implementation of BeanMetadata
 *
 * @author <a href="mailto:dev@geronimo.apache.org">Apache Geronimo Project</a>
 * @version $Rev: 760378 $, $Date: 2009-03-31 11:31:38 +0200 (Tue, 31 Mar 2009) $
 */
public class BeanMetadataImpl extends ComponentMetadataImpl implements MutableBeanMetadata {

    private String className;
    private String initMethodName;
    private String destroyMethodName;
    private List<BeanArgument> arguments;
    private List<BeanProperty> properties;
    private int initialization;
    private String factoryMethodName;
    private Target factoryComponent;
    private String scope;
    private Class runtimeClass;
    private List<String> explicitDependencies;
    private boolean processor;

    public BeanMetadataImpl() {
    }

    public BeanMetadataImpl(BeanMetadata source) {
        super(source);
        this.className = source.getClassName();
        this.initMethodName = source.getInitMethodName();
        this.destroyMethodName = source.getDestroyMethodName();
        for (BeanArgument argument : source.getArguments()) {
            addArgument(new BeanArgumentImpl(argument));
        }
        for (BeanProperty property : source.getProperties()) {
            addProperty(new BeanPropertyImpl(property));
        }
        this.initialization = source.getInitialization();
        this.factoryMethodName = source.getFactoryMethodName();
        this.factoryComponent = MetadataUtil.cloneTarget(source.getFactoryComponent());
        this.scope = source.getScope();
        this.runtimeClass = source.getRuntimeClass();
        this.explicitDependencies = new ArrayList<String>(source.getExplicitDependencies());
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getInitMethodName() {
        return initMethodName;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public String getDestroyMethodName() {
        return destroyMethodName;
    }

    public void setDestroyMethodName(String destroyMethodName) {
        this.destroyMethodName = destroyMethodName;
    }

    public List<BeanArgument> getArguments() {
        if (this.arguments == null) {
            return Collections.emptyList();
        } else {
            return Collections.unmodifiableList(this.arguments);
        }
    }

    public void setArguments(List<BeanArgument> arguments) {
        this.arguments = arguments != null ? new ArrayList<BeanArgument>(arguments) : null;
    }

    public void addArgument(BeanArgument argument) {
        if (this.arguments == null) {
            this.arguments = new ArrayList<BeanArgument>();
        }
        this.arguments.add(argument);
    }

    public BeanArgument addArgument(Metadata value, String valueType, int index) {
        BeanArgument arg = new BeanArgumentImpl(value, valueType, index);
        addArgument(arg);
        return arg;
    }

    public void removeArgument(BeanArgument argument) {
        if (this.arguments != null) {
            this.arguments.remove(argument);
        }
    }

    public List<BeanProperty> getProperties() {
        if (this.properties == null) {
            return Collections.emptyList();
        } else {
            return Collections.unmodifiableList(this.properties);
        }
    }

    public void setProperties(List<BeanProperty> properties) {
        this.properties = properties != null ? new ArrayList<BeanProperty>(properties) : null;
    }

    public void addProperty(BeanProperty property) {
        if (this.properties == null) {
            this.properties = new ArrayList<BeanProperty>();
        }
        this.properties.add(property);
    }

    public BeanProperty addProperty(String name, Metadata value) {
        BeanProperty prop = new BeanPropertyImpl(name, value);
        addProperty(prop);
        return prop;
    }

    public void removeProperty(BeanProperty property) {
        if (this.properties != null) {
            this.properties.remove(property);
        }
    }

    public int getInitialization() {
        return initialization;
    }

    public void setInitialization(int initialization) {
        this.initialization = initialization;
    }

    public String getFactoryMethodName() {
        return this.factoryMethodName;
    }

    public void setFactoryMethodName(String factoryMethodName) {
        this.factoryMethodName = factoryMethodName;
    }

    public Target getFactoryComponent() {
        return this.factoryComponent;
    }

    public void setFactoryComponent(Target factoryComponent) {
        this.factoryComponent = factoryComponent;
    }

    public String getScope() {
        return this.scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Class getRuntimeClass() {
        return this.runtimeClass;
    }

    public void setRuntimeClass(Class runtimeClass) {
        this.runtimeClass = runtimeClass;
    }

    public List<String> getExplicitDependencies() {
        if (this.explicitDependencies == null) {
            return Collections.emptyList();
        } else {
            return Collections.unmodifiableList(this.explicitDependencies);
        }
    }

    public void setExplicitDependencies(List<String> explicitDependencies) {
        this.explicitDependencies = explicitDependencies != null ? new ArrayList<String>(explicitDependencies) : null;
    }

    public void addExplicitDependency(String explicitDependency) {
        if (this.explicitDependencies == null) {
            this.explicitDependencies = new ArrayList<String>();
        }
        this.explicitDependencies.add(explicitDependency);
    }

    public void removeExplicitDependency(String dependency) {
        if (this.explicitDependencies != null) {
            this.explicitDependencies.remove(dependency);
        }
    }

    public boolean isProcessor() {
        return processor;
    }

    public void setProcessor(boolean processor) {
        this.processor = processor;
    }

    @Override
    public String toString() {
        return "BeanMetadata[" +
                "id='" + id + '\'' +
                ", className='" + className + '\'' +
                ", initMethodName='" + initMethodName + '\'' +
                ", destroyMethodName='" + destroyMethodName + '\'' +
                ", arguments=" + arguments +
                ", properties=" + properties +
                ", initialization=" + initialization +
                ", factoryMethodName='" + factoryMethodName + '\'' +
                ", factoryComponent=" + factoryComponent +
                ", scope='" + scope + '\'' +
                ", runtimeClass=" + runtimeClass +
                ", explicitDependencies=" + explicitDependencies +
                ']';
    }
}
