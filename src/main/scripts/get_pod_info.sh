#!/bin/bash

#
# Copyright (C) @2020 Webank Group Holding Limited
# <p>
# Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
# in compliance with the License. You may obtain a copy of the License at
# <p>
# http://www.apache.org/licenses/LICENSE-2.0
# <p>
# Unless required by applicable law or agreed to in writing, software distributed under the License
# is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
# or implied. See the License for the specific language governing permissions and limitations under
# the License.
#

ADDR="127.0.0.1:10000"
scripts="get_pod_info.sh"

function printEg() {
        echo "param refer."
        echo "get pod info by pod name, eg : sh ${scripts} -p "
        echo "get pod info by host ip, eg : sh ${scripts} -h "
        echo "get pod info by pod set id, eg: sh ${scripts} -i "
        echo "get pod info by subsystem , eg : sh ${scripts} -s Dockin-toolkit"
        echo "get pod info by subsystem and dcn, eg : sh ${scripts} -s Dockin-toolkit 1A0"
}

if [ $# -eq 1 ]
then
    if [ "$1" = -s ]
    then
        curl "http://${ADDR}/rmController/getPodInfoBySubsystem?"
        exit 0;
    else
        printEg;
        exit 1;
    fi
fi

if [ $# -lt 2 ]
then
    printEg;
    exit 1;
fi

case $1 in
    -p)
        curl "http://${ADDR}/rmController/getPodInfoByPodName?podName=$2";;
    -h)
        curl "http://${ADDR}/rmController/getPodInfoByHostIp?hostIp=$2";;
    -i)
        curl "http://${ADDR}/rmController/getPodInfoByPodSetId?podSetId=$2";;
    -s)
        if [ $# -eq 2 ]
        then
            curl "http://${ADDR}/rmController/getPodInfoBySubsystem?subsystem=$2"
        else
            curl "http://${ADDR}/rmController/getPodInfoBySubsystem?subsystem=$2&dcn=$3"
        fi;;
    *)
        printEg;
        exit 1;;
esac
