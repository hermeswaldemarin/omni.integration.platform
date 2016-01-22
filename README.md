## Omni Integration Platform
The Omni Integration Platform provides a core environment to support the adapters that will be written to develop integrations for Omni-Plusoft.

This Integration Platform is built on top of SpringBoot Framework and all Spring Modules can be used in the adapters.

Will be build adapter to this Integration Platform and this adapters are just a java maven project depending on this Integration Platform.

When the clean package command is executed in the adapter project two jars will be generated.

NAMEOFADAPTER-VERSION-SNAPSHOT.jar - This file contains all SpringBoot dependencies and this jar will not be used.

NAMEOFADAPTER-VERSION-SNAPSHOT.jar.original - This file contains only the classes that Integration Platform will need. This file will be renamed do .jar and send to adapter folder.

To start Omni Integration Platform use a command like this

java -cp "./IntegrationPlatform.jar:./adapter/*:./adapter/lib/*" org.springframework.boot.loader.JarLauncher

This command will start the Integration Platform putting the adapters and adapter libraries on the memory, 
