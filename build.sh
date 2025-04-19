#!/bin/bash

set -e

./gradlew -Pvaadin.productionMode=true --no-parallel --include-build jmix-petclinic-2 :jmix-petclinic-2:clean :jmix-petclinic-2:bootBuildImage -x test --no-build-cache
./gradlew -Pvaadin.productionMode=true --no-parallel --include-build jmix-petclinic-portal :jmix-petclinic-portal:clean :jmix-petclinic-portal:bootBuildImage -x test --no-build-cache