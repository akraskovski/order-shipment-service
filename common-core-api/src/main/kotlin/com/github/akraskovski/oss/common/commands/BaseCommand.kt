package com.github.akraskovski.oss.common.commands

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.io.Serializable
import javax.validation.constraints.NotNull

open class BaseCommand<T : Serializable>(@NotNull @TargetAggregateIdentifier val id: T)