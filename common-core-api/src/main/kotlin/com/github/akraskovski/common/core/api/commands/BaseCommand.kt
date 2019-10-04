package com.github.akraskovski.common.core.api.commands

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.io.Serializable

open class BaseCommand<T : Serializable>(@TargetAggregateIdentifier val id: T)