#!/bin/bash
rm -r target/
export VERSION="NON-PRIMITIVE-1.0"
# export VERSION="PRIMITIVE-1.0"
mvn clean package
scp target/transaction-analyzer-$VERSION.jar arjun@10.8.0.25:/home/arjun/WD/expt/Verification/TA-Old/
