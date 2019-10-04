package com.github.akraskovski.oss.common.commands

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.io.Serializable

open class BaseCommand<T : Serializable>(@TargetAggregateIdentifier val id: T)